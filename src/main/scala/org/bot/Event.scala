package org.bot

import com.github.nscala_time.time.Imports.DateTime

case class Event(
                  emknId: ID,
                  description: String,
                  sourceType: SourceType,
                  lastModificationTime: DateTime,
                  deadline: DateTime,
                  link: URL
                )
