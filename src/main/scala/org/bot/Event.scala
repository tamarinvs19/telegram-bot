package org.bot

import com.github.nscala_time.time.Imports.DateTime

case class Event(
                  emknId: ID,
                  description: String,
                  sourceType: SourceType,
                  creationDate: DateTime,
                  deadlineDate: DateTime,
                  link: URL
                )
