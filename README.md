# Ultimate English Vocabulary Game (OLD - 2020 project)

Note: You might see as contributor Alex-Tela, which is the GitHub account where I originally stored this repository. I have now moved it here in order to have all my past and future projects centralized.

This is an old Android application made in Java in Android Studio. Its purpose is to help you practice your English vocabulary through fun mini games, the core one being similar to a Romanian spelling game called "FAZAN". You can find this app on Google Play Store here: https://play.google.com/store/apps/details?id=com.alextela.myapplication

This application contains many features, such as:
- a user account where you can see your progress.
- the ability to play against a friend on the same phone.
- responsiveness, meaning that there is a special layout created for tablet users.
- many game modes to choose from.

## Game modes

*Player vs. AI*: The user has to enter a word that is directly related to the last letters of the AI's word. The user wins when the AI cannot create a word anymore out of the user's word. The objective is not to win as soon as possible, but to be able to write as many words as the user can.

*Player vs. Player*: This game mode is intended to be played on one phone by 2 friends in situations such as when traveling by train, airplane, bus, or scenarios like school/work breaks. Rules are similar to the previous game mode. However, the purpose is exactly the opposite, meaning to win as soon as possible.

*Complete the Word*: The user is given a word that has empty spaces, having to find out which are the correct missing letters.

*Word Bonanza*: Being given a letter by the AI, the player has to write as many words as possible starting with that particular letter before the timer stops, therefore testing the capability of memorizing words.

## Mistakes

This application was created at a time when I just started learning about programming (specifically in my first year of University). Therefore, you will see many mistakes throughout the application, such as the prevalent use of `if` statements, the fact that secrets, such as the MoPub advertisement keys for interstitials are not stored as environment variables, the poor naming conventions, and the fact that a proper database was not used. 

## Optimizations

Although the application works perfectly well, it could have been created in a better way. For example, an efficient way to store and make use of the words in the English dictionary would have been to store them in, for example, a SQLite database instead of in XML files. Another advantage of using a database would have been to store the user accounts in there instead of using a person's mobile local storage (`SharedPreferences`).

However, please bear in mind that this was a beginner's project.

## Maintenance

This application is no longer maintained unfortunately, as my focus has changed from Android mobile applications to web applications (that include Java for the back-end).

