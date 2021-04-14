package org.bot.data.base
import org.bot.storage.classes.{calendar, event}
import org.bot.user.user

object dataBase {
  def addNewEvent(user: user, event: event) = ???
  def removeEvent(event: event) = ???
  def updateCalendar(calendar: calendar) = ???
  def updateUsersCalendars(user: user) = ???
  def deleteOutdatedEvents() = ???
}
