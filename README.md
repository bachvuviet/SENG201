SENG201 Assignment
Start date: 26/03/19
Author: Bach Vu, Linh Luu
Project: SpaceExplorer

Ignore bin folder: git rm -r --cached bin
Linux: cd Location, java -jar bvv10_kll60_SpaceExplorer.jar

Documentation, diagrams and relavent resource can be found in Documentation folder.

------- How to play: (Tutorial built-in game) ----------
* Hover mouse on progress bar to view actual value. Most UI has tooltip setup to explain current status.
* Left click to undo action

1- Press ESC to assess ship situation
2- Now select Crew tab to visit our crew.
3- Order two crews to Pilot ship using dropdown. Same way can be used to give different orders.
Order a pilot to eat something (Supply box below avatar)

4- Use: W to go forward, S to reverse, A to steer left, D to steer right. 
Remember, you cannot control the ship when Plasma fuel ran out (display in bottom-right corner) and at least 2 crews to Pilot the ship.

5- Press X to enter spacestation (must be close to one)
6- Scan a planet for possible transporter. Press X
7- Press Enter to refuel and reset crew action.

------------------- Quick tips -------------------------
Sleep: +30 Morale, -20 Hunger
Pilot: -30 Morale, -30 Hunger
Repair: -30 Morale, -30 Hunger
Use Supplement: Increase HP, Hunger or morale (depends on item)

Stock: ($100 default)
	Burger: 		 +5 Hunger, Price:  -2$, default Amount: 5
	Bread: 			+10 Hunger, Price:  -4$, default Amount: 2
	Pizza: 			+15 Hunger, Price:  -6$, default Amount: 0
	Chicken: 		+20 Hunger, Price:  -7$, default Amount: 0
	Steak: 			+25 Hunger, Price:  -8$, default Amount: 0
	Sushi: 			+30 Hunger, Price:  -9$, default Amount: 0
	Heal Potion: 	+20 Health, Price: -10$, default Amount: 1
	Pain Killer: 	+20 Morale, Price: -10$, default Amount: 1
	Syringe: 		+10 Health, Price: -10$, default Amount: 1, heal desease
	
If crew is sick, -50HP per turn
Crew dies if run out of health

Captain: Boost base morale of all crew by 25
Doctor: Boost base health of all crew by 25
Helms: He/She boosts your spaceship fuel by 200
Mechanic: He/She fixes your ship faster
Scientist: He/She increase Ship HP by 50
Chef: Boost base hunger of all crew by 25

------------------ Extra: ------------------------
Save/Load game (*.starman)
