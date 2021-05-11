package org.bot

import org.bot.ConcurrentHashMap.HavingId

case class Calendar(user: User, events: List[Event])

object Calendar {
  implicit val calendarId: HavingId[Calendar] = (item: Calendar) => item.user.emknId
}
