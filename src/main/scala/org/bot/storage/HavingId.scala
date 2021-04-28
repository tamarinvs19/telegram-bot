package org.bot.storage

import org.bot.ID

trait HavingId[T] {
  def id(item: T): ID
}

