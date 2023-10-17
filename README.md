# retrospect
coding exercise for sys

I have used spring boot rest api to create rest API end point because which easy to create production ready Rest APIs.



DESIGN RELATIONSHIP between Retrospect ---> ScrumTeamMember relationship is oneToMany
Retrospect class has retroName is Pk
ScrumTeamMember has composite Primary Key(retroName, scrumTeamMemberCode)
Relationship between Retrospect ---> RetrospectFeedbck is oneToMany 
RetrospectFeedbck has Composite Primary Key (Id, RetroName and ScrumTeamMemberCode )



