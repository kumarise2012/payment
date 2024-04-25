
SMS - STOCK MANIPULATION SYSTEM

Task
- File will be received on every 2 mins
- Load file data in H2/any in mem database
- Find the traders who have Buy/Sell any perticuler stock more than 5 times in last 10 mins.
- Mark that Trader as Fraud or Smthing or identification
- Call the Regulatory service of 2 countries and share the Fraud trader details
- Regulatroy service will write Trader details on Active Mq
- There shoud be one server which will read data from Active MQ

Before starting application make sure you Active MQ Server is up and running.

H2 database - http://localhost:9091/h2-console

http://localhost:8161/admin/queues.jsp

Consumer-web-service-
http://localhost:9091/consume/message

Regulatory Service - of two countries
http://localhost:9091/regulatoryAuthorities/country/india
http://localhost:9091/regulatoryAuthorities/country/japan


JSON DATA to send-
{
"traderId": "ZE001",
"stockId": "ITC4321",
"firstName": "Santosh",
"lastName": "Kumar",
"nationality": "IN",
"countryOfResidence": "IN",
"date_of_birth": "30-11-1990",
"fraudDetectionTime": "11:26:00"
}




















