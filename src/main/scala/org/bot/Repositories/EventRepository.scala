package org.bot.Repositories

import com.github.nscala_time.time.Imports.DateTime
import org.bot.Event

trait EventRepository[F[_], EventId] extends Repository[F, EventId, Event] {
  def getEvents(begin: DateTime, end: DateTime): F[List[Event]]
}
