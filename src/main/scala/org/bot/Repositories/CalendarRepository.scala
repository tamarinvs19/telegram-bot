package org.bot.Repositories

import org.bot.Calendar

trait CalendarRepository[F[_], CalendarId] extends Repository[F, CalendarId, Calendar] {

}
