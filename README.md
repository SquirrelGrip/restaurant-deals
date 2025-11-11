# Task 1
## Starting Thoughts
- Download the data from the URL provided
  - https://eccdn.com.au/misc/challengedata.json
- Create an data object for the downloaded data. 
- Store the data in a map for the time being.
- Load the data from resources at startup.
- Create a Map based repository for querying/storing the data.

### Spring Time!!!
- Time for some web framework. I have selected Spring, only because it is easy to use...debatable said the newbie :)

# Task 2
## Starting Thoughts
- Using the DealService.streamAllDeals() I can iterate over all deals and add each entry to a Sorted Map<LocalTime, Int). 
- Each deal would increment the start time and decrement the end time
- Once the map is built, turn the map into a list (which will be sorted).
- For each entry, create an Interval which has a start, end and count
  - The count is the current count plus the delta of that time.
  - We would need a BiFunction or Local Variable to store the temporary value for the next iteration.
- Then Find the Interval with the highest count.
- What if there are 2, or more, intervals with same max value? Luckily the data doesn't suggest that.
- I am predicting from Task 1 that the peak should be around 6pm to 9pm, but certainly not 3pm.