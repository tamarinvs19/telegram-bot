package org.bot.ConcurrentHashMap

import org.bot.{Calendar, Event, User}
import org.bot.storage.HavingId

object IdImplicits {
  implicit val userId: HavingId[User] = (item: User) => item.emknId
  implicit val eventId: HavingId[Event] = (item: Event) => item.emknId
  implicit val calendarId: HavingId[Calendar] = (item: Calendar) => item.user.emknId
}