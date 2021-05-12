package org.bot.traits

import org.bot.Calendar

trait CalendarRepository[F[_], CalendarId] extends Repository[F, CalendarId, Calendar] {

}
