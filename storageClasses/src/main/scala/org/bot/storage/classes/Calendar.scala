package org.bot.storage.classes

case class Calendar(id: Int,
                    siteLogin: String,
                    sitePassword: String,
                    eventsList: List[Int]) {

}
