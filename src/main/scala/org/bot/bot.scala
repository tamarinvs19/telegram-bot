package org

import cats.{Eval, Monad, Now}
import cats.effect.{ExitCase, Sync}

import scala.util.control.NonFatal

package object bot {
  final case class ID(v: Int) extends AnyVal
  final case class URL(v: String) extends AnyVal

  implicit object evalSync extends Sync[Eval] {
    private val delegate: Monad[Eval] = Eval.catsBimonadForEval

    def pure[A](x: A): Eval[A] = delegate.pure(x)

    def handleErrorWith[A](fa: Eval[A])(f: Throwable => Eval[A]): Eval[A] =
      Eval.defer(try Now(fa.value) catch { case NonFatal(t) => f(t) })

    def raiseError[A](e: Throwable): Eval[A] = Eval.defer(throw e)

    def flatMap[A, B](fa: Eval[A])(f: A => Eval[B]): Eval[B] =
      delegate.flatMap(fa)(f)

    def tailRecM[A, B](a: A)(f: A => Eval[Either[A,B]]): Eval[B] =
      delegate.tailRecM(a)(f)

    override def delay[A](thunk: => A): Eval[A] = Eval.always(thunk)

    def suspend[A](thunk: => Eval[A]): Eval[A] = Eval.defer(thunk)

    override def bracketCase[A, B](acquire: Eval[A])(use: A => Eval[B])(release: (A, ExitCase[Throwable]) => Eval[Unit]): Eval[B] = {
      flatMap(acquire) { a =>
        try {
          val b = use(a)
          try release(a, ExitCase.Completed) catch { case NonFatal(_) => }
          b
        } catch {
          case NonFatal(e) =>
            release(a, ExitCase.Error(e))
            raiseError(e)
        }
      }
    }
  }
}
