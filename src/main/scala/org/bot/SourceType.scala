package org.bot

sealed trait SourceType

case object ClassesSourceType extends SourceType
case object TasksSourceType extends SourceType
case object EventsSourceType extends SourceType
