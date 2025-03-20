<<<<<<< HEAD
## Getting Started

Welcome to the VS Code Java world. Here is a guideline to help you get started to write Java code in Visual Studio Code.

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).
=======
# SilentSymbols
1. Project Name: Silent Symbols
   Team Name: ASL Educators
   Team Members: Nicky Raybould and Clara Yalamanchili
2. We are trying to build a user-friendly application for aspring ASL learners to test them on words and letters in ASL. We want to build this app because we are learning ASL ourselves and want to apply our knowledge from our other classes. This application will be useful for people who are starting to learn ASL because they can gain a basic foundation in this useful language at the convenience of their device. Silent Symbols will involve a folder of signs from the alphabet for the "easy" level, which will be for beginners just starting. We will also have a word bank of different basic words in ASL and their sign, and users will be tested on what that given word or sign is, and they will have to input the answer.
3. We are designing the following classes:
   - userLetter, where the user will enter a letter based on the sign in the image provided, for the easy level
   - Image, which will include the folder of the file names with the images of the signs, each being stored in a String. Will either display 1 image at a time for the easy level (a singular letter) or multiple at a time for the easy level (words)
   - skillLevel, where the user will enter 1 for the easy level or 2 for the hard level
   - userLetter, where the user will input a letter based on the sign image provided for the easy level
   - userWord, where the user will input a word based on the sign image provided for the hard level
   - Easy, where the methods / interfaces of the easy level will occur
   - Hard, where the methods / interfaces of the hard level will occur
   We are designing the following interfaces:
   - letterValidity, which defines the method isValidLetter to check if the userAnswer (user's input) is a valid letter in the alphabet in easy level
   - wordValidity, which defines the method isValidWord to check if the userAnswer is a valid word or string of characters in the hard level
   - skillValidity, which defines the method isValidSkill to check if the user's skillAnswer (user's input of 1 or 2) is one of the two integer options
4. Here is our plan to complete the design and implementation:
   - 2 days spent on creating the GUI - creating the window with JavaFX, designing the layout: buttons to navigate between levels, labels, images
   - 2 days spent on creating our classes: userLetter, userWord, Image, and SkillLevel. Writing code to define variables, methods, and attributes
   - 1 day to add logic to the skillLevel class and implement method that makes sure user gives valid input (1 or 2)
   - 1 day to add logic to the Image class to display an image file based on the user's skill level
   - 2 days to add logic to the userLetter and userWord classes to store the correct letter/word and validate user input
   - 2 days to add logic to the validity interfaces take input for one letter at a time, words, storing and displaying image files, and setting the level based on user input of 1 or 2
   - 2 days to add logic for Easy and Hard classes - might not need these classes and could have the different logic handled in SkillLevel class?
   - 2 days to connect logic with GUI
   - 3 days to test application, fix bugs, and make necessary changes
   - 3 days to prepare and practice presentation
   
>>>>>>> origin/main
# SilentSymbols
