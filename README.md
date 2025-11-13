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

# Task 3
- See database.png in project root directory. I have included rudimentary fields to give a gist of the schema design.
- I would probably use a relational database such as PostgreSQL for this design, although a document store would also suffice, eg MongoDB, etc.
- My preference for RDBMS is MySQL as I run it at home, although it is never a choice in any enterprise environment. It suits my needs hence why I would choose it.

However, without knowing all of the following considerations, whatever choice I make I would be giving incorrect advice.

### Considerations
- Cost of Running
  - how much does it cost to run the db (in cloud, on prem, etc.)
  - how much big a community is there around supporting the DB
  - how many people are readily available to fix things when things go wrong
  - Is there help when things go wrong?
  - Licensing Costs
  - Support Costs
- Cost of Changing DB
  - Is it easy to move away from if support is no longer available or becomes cost prohibitive.
- Ease of Changes
  - Is it easy to make changes to the database schema.
    - A good DDL is built into the DB
  - Can anyone access the data if they need to get data from it (security allowing) through a set of tools.
- Performance
  - It should be fast, but should not be the first consideration.
  - What sort of indexes does it create?
  - Can Temporary tables be used.
- Scalability
  - Can the database grow beyond a single node.
  - Can I shard data across geographic boundaries.
- Reliability
  - Can it be backed up? how easy is that?
  - How easy is it to restore?
  - Does it support replication, if needed.
  - Does it auto failover
    - Can we lose a node at anytime and still have full write access.
  - Is it susceptible to "split brain"?
- Transaction Support
  - Not all DBs fully support ACID transactions. This is a must in certain environments (especially when money is involved)
- Auditable
  - Can you determine what happened 
  - Who did what and when.
- Secured
  - Is the database access controllable
  - Can it lock down to a person/entity
  - How easily can access be block to a user
  - What authentication methods are available
- Language Support
  - how can we access the DB. Can we use JDBC, Open Libraries or must we use a commercial driver.
  - Does it have support for the language we use?
  - Does it support standard languages such as SQL.
- Time base querying
  - Are we using the DB for time based logging or event capturing

As not all of these are important to everyone, however, it can make a significant difference when selecting from the 100s of choices that are available.

## PostgreSQL
- In a cloud environment, PostgreSQL is probably a good choice as they are relative cheap and have good transaction support.
- There is a rich community around PostgreSQL. 
- It can support large datasets.
- PostgreSQL has good support for geographic data, which maybe a requirement if finding restaurants in current location is required
    - However, this can be achieved through other means and only is a positive, if using the geo location services with native query calls.
- PostgreSQL also has transaction control built in with good database backup and restore capabilities.
- Also realtime (or near realtime) replication support if required.
- Data Sharding is also a good addition if wanting to replicate data to data nodes closer to users (i.e in that restaurants in Europe are not really going to be queried in Australia).
- You would need to implement good database configuration management, such as Liquibase of FlyWay to achieve consistent database upgrades.

## H2
- For Local Testing, H2 is great for creating, and destroying, DBs while testing. 
- Does not have any of the features you would want in production
- Given what it is used for, it suits the very purpose well.

## MongoDB
- Another good choice however, in this situation, probably not the best fit as you would need more complex entity management built into your services.
    - For example, adding a cuisine to a restaurant would mean editing the entire restarant using mongodb, but with a RDBMS, this is a simple insert statement.
- MongoDB would be fast overall from a performance point of view
    - However, this positive is usually undermined by the fact that the economics of a non-RDBMS can run higher as there are limited people that know the DB.
    - Technically MongoDB is awesome. Realistically it some drawbacks that only appear after you start using it for a while.
- Querying is non-standard, i.e. Does not use SQL.

