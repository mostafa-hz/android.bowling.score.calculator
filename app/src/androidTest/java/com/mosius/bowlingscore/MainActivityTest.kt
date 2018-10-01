package com.mosius.bowlingscore

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Rule

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val testRule = ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Test
    fun performExampleGame() {
        onView(withId(R.id.text_game_score))
                .check(matches(withText("Score: 0")))

        onView(withId(R.id.button_1))
                .perform(click())
        onView(withId(R.id.button_4))
                .perform(click())

        onView(withId(R.id.text_game_score))
                .check(matches(withText("Score: 5")))

        onView(withId(R.id.button_4))
                .perform(click())
        onView(withId(R.id.button_5))
                .perform(click())

        onView(withId(R.id.text_game_score))
                .check(matches(withText("Score: 14")))

        onView(withId(R.id.button_6))
                .perform(click())
        onView(withId(R.id.button_4))
                .perform(click())

        onView(withId(R.id.text_game_score))
                .check(matches(withText("Score: 14")))

        onView(withId(R.id.button_5))
                .perform(click())
        onView(withId(R.id.button_5))
                .perform(click())

        onView(withId(R.id.text_game_score))
                .check(matches(withText("Score: 29")))

        onView(withId(R.id.button_10))
                .perform(click())

        onView(withId(R.id.text_game_score))
                .check(matches(withText("Score: 49")))

        onView(withId(R.id.button_0))
                .perform(click())
        onView(withId(R.id.button_1))
                .perform(click())

        onView(withId(R.id.text_game_score))
                .check(matches(withText("Score: 61")))

        onView(withId(R.id.button_7))
                .perform(click())
        onView(withId(R.id.button_3))
                .perform(click())

        onView(withId(R.id.text_game_score))
                .check(matches(withText("Score: 61")))

        onView(withId(R.id.button_6))
                .perform(click())
        onView(withId(R.id.button_4))
                .perform(click())

        onView(withId(R.id.text_game_score))
                .check(matches(withText("Score: 77")))

        onView(withId(R.id.button_10))
                .perform(click())

        onView(withId(R.id.text_game_score))
                .check(matches(withText("Score: 97")))

        onView(withId(R.id.button_2))
                .perform(click())
        onView(withId(R.id.button_8))
                .perform(click())
        onView(withId(R.id.button_6))
                .perform(click())

        onView(withId(R.id.text_game_score))
                .check(matches(withText("Score: 133")))

    }

    @Test
    fun performAllStrikeGame() {
        onView(withId(R.id.text_game_score))
                .check(matches(withText("Score: 0")))

        repeat(12) {
            onView(withId(R.id.button_10))
                    .perform(click())
        }

        onView(withId(R.id.text_game_score))
                .check(matches(withText("Score: 300")))

    }

    @Test
    fun performGameRestart() {
        onView(withId(R.id.text_game_score))
                .check(matches(withText("Score: 0")))

        repeat(12) {
            onView(withId(R.id.button_10))
                    .perform(click())
        }

        onView(withId(R.id.text_game_score))
                .check(matches(withText("Score: 300")))

        onView(withId(R.id.button_restart))
                .perform(click())

        onView(withId(R.id.text_game_score))
                .check(matches(withText("Score: 0")))
    }
}
