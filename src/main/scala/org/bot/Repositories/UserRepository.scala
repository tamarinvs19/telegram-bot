package org.bot.Repositories

import org.bot.User

trait UserRepository[F[_], UserId] extends Repository[F, UserId, User] {

}
