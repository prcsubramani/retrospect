# retrospect
coding exercise for sys

DESIGN RELATIONSHIP between Retrospect ---> ScrumTeamMember relationship is oneToMany
Retrospect class has retroName is Pk
ScrumTeamMember has composite Primary Key(retroName, scrumTeamMemberCode)
Relationship between Retrospect ---> RetrospectFeedbck is oneToMany 
RetrospectFeedbck has Composite Primary Key (Id, RetroName and ScrumTeamMemberCode )



