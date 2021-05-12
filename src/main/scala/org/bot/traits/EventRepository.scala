package org.bot.traits

import com.github.nscala_time.time.Imports.DateTime
import org.bot.{Event, SourceType}

trait EventRepository[F[_], EventId] extends Repository[F, EventId, Event] {
  def getEvents(begin: DateTime, end: DateTime, sourceType: SourceType): F[List[Event]] =
    filter(event => event.deadlineDate.isAfter(begin) &&
        event.deadlineDate.isBefore(end) &&
        event.sourceType == sourceType
    )
}
