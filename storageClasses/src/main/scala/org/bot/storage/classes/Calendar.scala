package org.bot.storage.classes

case class Calendar(id: Int,
                    siteName: String,
                    siteLogin: String,
                    sitePassword: String,
                    eventsList: List[Int]) {

}
