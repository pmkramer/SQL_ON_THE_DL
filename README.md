# SQL_ON_THE_DL

## Cookbook Database

### Summary of what the Program does:
- User can search for recipes, create new recipes, update recipes.

### Tables:
  - Recipes
    - id (PRIMARY KEY)
    - name
    - instructions
    - category 
    - calories
  - Ingredients
    - name (PRIMARY KEY)
    - price
    - category (spice / vegetable / fruit / etc.)
  - Recipe Ingredients
    - ingredient_id (FOREIGN KEY)
    - recipe_id (FOREIGN KEY)
  
  - Users
    - favorite_recipes
 
### Deliverables:
 - [ ] source code and executable
 - [ ] slides for the presentation
 - [ ] log file: who did what and when, how much time was spent on each task
 - [ ] user documentation: detailed explanation of how to install / use your system, including password information if applicable
 - [ ] a description of the internals of the program. How is the different functionality supported.

## Getting Set Up:
 - Download / Install Netbeans
 - Open Project From Netbeans
 - Follow Stanchev's Slides For Connecting To Database
