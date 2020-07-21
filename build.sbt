name := """personExercise"""
organization := "com.fabernovel"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.13.1"

libraryDependencies += guice

// https://mvnrepository.com/artifact/org.mongodb/mongodb-driver-sync
libraryDependencies += "org.mongodb" % "mongodb-driver" % "3.12.1"

libraryDependencies += javaForms