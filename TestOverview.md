# Overview
## Channel
### AssignmentChannel
- UpdateNick

   Try changing the nickname of a single client.
   
- quit1

   Is the quit message correct?
   
- quit2

  See quit1.
  
### JoinChannel
- testJoin1

   Try joining a channel.
   
- testJoin2
   
   Joining a channel and try joining it again.
   
- testJoin3

   See testJoin1.
   
- testJoin4

   Joining a channel check if join message is correctly relayed.
   
- testJoin5

   See testJoin4.

### ListChannel
- list1

   Let multiple clients join a single channel and send the list command.
   
- list 2

   Let multiple clients join multiple channels. One channel with topic and one without one.
   
- list3

   See list1.
   
- list4

   Let one client join a channel and set the topic. Now, two other clients join the channel.

### PartChannel
- joinAndPart1

   Two clients join a channel and one of them send the part command.
   
- joinAndPart2

   See joinAndPart1 with custom part message.
   
- joinAndPart3

   Two clients join a channel. One sends a private message and then send the part command.

- joinAndPart4
   
   Two clients join a channel and then send the part command.
   
- partNoChannel1

   Try sending the part command to a non-existing channel.
   
- partNoChannel2

   Client join a channel and send the part command twice.
   
- partNotOnChannel1

   Try sending the part command without joining a channel.
   
- partNotOnChannel2

   See partOnChannel1

### PrivateMessageChannel
- joinAndMessage1

   Two clients join a channel and one sends a private message.
   
- joinAndMessage2

   Multiple clients join the same channel and sending each other private messages.
   
- joinAndMessageNoChannel

   Send a private message to a not existing channel.
   
- joinAndNoticeNoChannel

   Send a notice to a not existing channel.
   
- joinAndMessageNotOnChannel

   Send a private message to channel the client have not joined.
   
### TopicChannel
- topic1

   Join a channel and set the topic.
   
- topic2

   Join a channel, set the topic and then get the topic.
   
- topic3

   Get the topic form a recently joined channel.
   
- topic4

   Get the topic from a channel which is not joined.
   
- topic5

   Set the topic from a channel which is not joined.
   
- topic6

   Set the topic and get it then.
   
- topic7

   See topic4
   
- topic8

   Set the topic and then another client joins the channel.
   
- topic9

   See topic8

### WhoChannel
- who1

   Send who command to multiple channels with multiple clients joined.
   
- who2

   Send 'who *' command.
   
- who3

   See who2
   
- who4

   See who1

## Connection
## MOTD
## PING
## Privmsg
## Robustness
## Unknown
## WhoIs

# Needed tests for assigments
## Assigment 1: Basic Message Processing
- BasicConnection (Except for nickUserParsing4, nickUserParsing5, noUnexpectedWelcome4 and noUnexpectedWelcome5)
- FullConnection

## Assigment 2: Supporting Multiple Clients
## Assigment 3: Channels and Modes