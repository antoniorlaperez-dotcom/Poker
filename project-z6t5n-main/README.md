# My Personal Project

I play poker with my friends in Ottawa. When we do, we struggle to allocate everyones winnings and losings at the end of the game based on everyones buy-in. It is tricky because we aren't in a casino---we can't cash in our winnings they come from other player's losings. 

This inspired my poker app project where I thought the first function of the project should be to calculate how much each person lost or won and organize who pays who. I expanded on the topic of poker and created another function below.

## Overview of what my prject does: 

- calculate how much each person lost or won and organize who pays who

- based on inputed players hands the project will be print who has the strongest hand preflop.

## Who will use it?

Anybody who is playing backyard poker and needs to decide who owes who money at the end as things can get messy as eveyrones hot headed and can't do math right which leads to people not paying the right amount. It can also be used in a little fun way to see who has the strongest hand preflop if your someone not currently playing and curious. Although you could probaly do this yourself with knowledge about highcards and pairs but everyone makes mistakes and this is a certain and fun little function that can help you wiht that. 

## User Stories

- As a user, I want to be able to add a person to a list of players in the app

- As a user, I want to be able to remove a person from a list of players in the app

- As a user, I want to be able to view the list of players playing 

- As a user, I want to be able to change the chip values 

- As a user, I want to be able to set the chip values

- As a user, I want to be able to see who has the strongest hand before the flop

- As a user, I want to be able to input everyones resulting chips and know who pays who in the end

- As a user, when I select the quit anytime, I want to be reminded to save my poker application to file and have the option to do so or not.

- As a user, when I start the application, I want to be given the option to load my poker application from file.

# Instructions for End User
- You can view the panel that displays the players that have already been added to the list of players by clicking the "view players" button
- You can generate the first required action related to the user story adding multiple players to a list of players by clicking the "add players button"
- You can generate the second required action related to the user story removing multiple players from the list of players by clicking the "remove players" button.
- You can locate my visual component when opening the application.
- You can save the state of my application by clicking the "save players" button.
- You can reload the state of my application by clicking the "load players" button.


# Phase 4: Task 2
Event Log:
Sun Mar 29 14:23:01 PDT 2026
Player added: Alice

Sun Mar 29 14:23:05 PDT 2026
Player added: Bob

Sun Mar 29 14:23:08 PDT 2026
Player added: Charlie

Sun Mar 29 14:23:12 PDT 2026
Player removed: Bob

# Phase 4: Task 3
When refractoring, the first thing that comes to mind is adding expections. For the UI phase of the project, my porject would only quit if a local variable was set to false. A better way would be to catch a quit exception. Secondly, I would put the 5 chips in pokerGame into a hashmap or an arraylist to limit simialr method repition. Thirdly and finnally, I woudld create an abstract class for the UI and the GUI as a good portion of both of these classes have simialr methods. 