package org.bot

import org.bot.ConcurrentHashMap.HavingId

case class User(emknId: ID, name: String)

object User {
  implicit val userId: HavingId[User] = (item: User) => item.emknId
}
