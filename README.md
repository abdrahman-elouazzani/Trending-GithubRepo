# Trending-Repositories
A small app that will list the most starred Github repos that were created in the last 30 days. You'll be fetching the sorted JSON data directly from the Github API .<br/>

## Features
- As a User you will be able to list the most starred Github repos that were created in the last 30 days.
- As a User you will be able to see the results as a list. One repository per row.
- As a User you will be able to see for each repo/row the following details :
  1. `Repository name.`
  2. `Repository description.`
  3. `Numbers of stars for the repo.`
  4. `Username and avatar of the owner.`
- As a User you will be able to keep scrolling and new results should appear (pagination)

## Installation
Clone this repository and import into **Android Studio**
```bash
git clone git@github.com:abdrahman-elouazzani/Trending-GithubRepo.git
```
##  Libraries Used
- The Front-End Using [the Material Design](https://material.io/design/) library contains : 
  1. [BottomNavigationView](https://material.io/components/bottom-navigation) component to navigated between the Trending and Setting.
  2. [RecyclerView](https://material.io/components/lists) to list the items (repository) on each row with Layout.
 ...<br/>
 
- The Back-end : call the api rest github repositories request using :
  1. [The Retrofit library](https://square.github.io/retrofit/).
  2. [The library Gson](https://github.com/google/gson) to create the JSON-OBJECT-REAUEST and JSON-ARRAY, JSON-OBECT for Parsing the data.
  3. [Rxjava].
  ... <br/>
  
## The Previews 

![Screenshot_1616087622](https://user-images.githubusercontent.com/40376977/111669071-b444d500-8816-11eb-9426-28136493576a.png)
![Screenshot_1616087632](https://user-images.githubusercontent.com/40376977/111669082-b73fc580-8816-11eb-8b5d-0ee71610bb39.png)
![Screenshot_1616087639](https://user-images.githubusercontent.com/40376977/111669088-b870f280-8816-11eb-9b96-dc51f8503835.png)
