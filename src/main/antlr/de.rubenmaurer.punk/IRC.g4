grammar IRC;

// IRC MAIN RULES
server_response
    : DLIMIT
    ( server_response_long
    | server_response_short )
    ;

server_response_long
    : server WHITESPACE code WHITESPACE nick
    ;

server_response_short
    : nick '!' user '@' host
    ;

message
    : (text | SPECIAL)+
    ;

text
    : (WORD | WHITESPACE | INTEGER)+
    ;

target
    : channel
    | nick
    ;

server
    : (WORD | SPECIAL)+
    ;

version
    : (WORD | SPECIAL | WHITESPACE | INTEGER)+
    ;

code
    : INTEGER
    ;

nick
    : WORD
    ;

user
    : (WORD | INTEGER)+
    ;

fullname
    : (WORD | WHITESPACE)+
    ;

host
    : WORD
    ;

channel
    : '#' WORD
    ;

command
    : WORD
    ;

date
    : (WORD
    | INTEGER
    | WHITESPACE
    | ':'
    | '-')+
    ;

// IRC SPECIAL RULES

/* === SERVER RESPONSE === */

response
    : private_message
    | notice
    | no_such_nick_channel
    | cannot_send_to_channel
    | no_motd
    | nickname_in_use
    | pong
    | who_is_user
    | who_is_server
    | end_of_who_is
    | unknown_command
    | welcome
    | your_host
    | created
    | my_info
    | luser_client
    | luser_op
    | luser_unknown
    | luser_channel
    | luser_me
    | motd_start
    | motd
    | end_of_motd
    ;

pong
    : 'PONG'
    ;

/* === WELCOME === */

welcome
    : server_response WHITESPACE DLIMIT 'Welcome to the Internet Relay Network' WHITESPACE server_response_short
    ;

your_host
    : server_response WHITESPACE DLIMIT 'Your host is' WHITESPACE server ',' WHITESPACE 'running version' WHITESPACE version
    ;

created
    : server_response WHITESPACE DLIMIT 'This server was created' WHITESPACE date
    ;

my_info
    : server_response WHITESPACE server WHITESPACE version WHITESPACE 'ao mtov'
    ;

/* === LUSER === */

luser_client
    : server_response WHITESPACE DLIMIT 'There are' WHITESPACE INTEGER WHITESPACE 'users and' WHITESPACE INTEGER WHITESPACE 'services on' WHITESPACE INTEGER WHITESPACE 'servers'
    ;

luser_op
    : server_response WHITESPACE INTEGER WHITESPACE DLIMIT 'operator(s) online'
    ;

luser_unknown
    : server_response WHITESPACE INTEGER WHITESPACE DLIMIT 'unknown connection(s)'
    ;

luser_channel
    : server_response WHITESPACE INTEGER WHITESPACE DLIMIT 'channels formed'
    ;

luser_me
    : server_response WHITESPACE DLIMIT 'I have' WHITESPACE INTEGER WHITESPACE 'clients and' WHITESPACE INTEGER WHITESPACE 'servers'
    ;

/* === MOTD === */

motd_start
    : server_response WHITESPACE DLIMIT '-' WHITESPACE server WHITESPACE 'Message of the day' WHITESPACE '-'
    ;

motd
    : server_response WHITESPACE DLIMIT '-' WHITESPACE message
    ;

end_of_motd
    : server_response WHITESPACE DLIMIT 'End of MOTD command'
    ;

/* === PRIVMSG & NOTICE === */

private_message
    : server_response WHITESPACE 'PRIVMSG' WHITESPACE target WHITESPACE DLIMIT text
    ;

notice
    : server_response WHITESPACE 'NOTICE' WHITESPACE target WHITESPACE DLIMIT text
    ;

/* === WHOIS === */

who_is_user
    : server_response WHITESPACE nick WHITESPACE user WHITESPACE host WHITESPACE '*' WHITESPACE DLIMIT fullname
    ;

who_is_server
    : server_response WHITESPACE nick WHITESPACE server WHITESPACE DLIMIT version
    ;

end_of_who_is
    : server_response WHITESPACE nick WHITESPACE DLIMIT 'End of WHOIS list'
    ;

/* === ERROR's === */

no_such_nick_channel
    : server_response WHITESPACE target WHITESPACE DLIMIT 'No such nick/channel'
    ;

cannot_send_to_channel
    : server_response WHITESPACE channel WHITESPACE DLIMIT 'Cannot send to channel'
    ;

no_motd
    : server_response WHITESPACE DLIMIT 'MOTD File is missing'
    ;

nickname_in_use
    : server_response WHITESPACE
    ;

unknown_command
    : server_response WHITESPACE command WHITESPACE DLIMIT 'Unknown command'
    ;

// IRC LEXER RULES
DLIMIT
    : ':'
    ;

WORD
    : (CHAR
    | SPECIAL)+
    ;

CHAR
    : [a-z]
    | [A-Z]
    ;

SPECIAL
    : '-'
    | '.'
    | '!'
    ;

WHITESPACE
    : ' '
    | '\t'
    ;

INTEGER
    : [0-9]+
    ;