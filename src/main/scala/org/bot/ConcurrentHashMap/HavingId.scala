package org.bot.ConcurrentHashMap

import org.bot.ID

trait HavingId[T] {
  def id(item: T): ID
}

