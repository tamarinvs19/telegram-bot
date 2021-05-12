package org.bot

import com.github.nscala_time.time.Imports.DateTime
import org.bot.ConcurrentHashMap.HavingId

case class Event(
                  emknId: ID,
                  description: String,
                  sourceType: SourceType,
                  creationDate: DateTime,
                  deadlineDate: DateTime,
                  link: URL
                )

object Event {
  implicit val eventId: HavingId[Event] = (item: Event) => item.emknId
}
