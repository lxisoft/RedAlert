var suggestions=
[
	'FIRE',
	'FLOOD',
	'MISSING',
	'THEFT',
	'ABUSE',
	'MOLESTATION',
	'FOLLOWING',
	'STRANDED',
	'ACCIDENT',
	'INJURED',
	'LANDSLIDE',
	'EXPLOSION'
]

var defenitions=
[
	'It\\\'s a fire over here please help.',
	'Flood: need immediate help.',
	'The person on the following attachment image has gone missing',
	'A theft had happened',
	'A person is trying to abuse me. Need urgent help.',
	'Molestation: Immediate help required. Location attached below.',
	'Someone\\\'s following me. I don\\\'t feel so good.',
	'I\\\'m stranded at some lonely place, need help to get back safe.',
	'Met with an accident, requires immediate help.',
	'Badly inured apreciated help from nearby friends.',
	'Distress due to land slide, immediate attention required.',
	'An explosion had happened, need attention to the locality.'
]



function showSuggestions() {	
	var buttondiv=document.getElementById('suggestions');
	buttondiv.innerHTML=null;
	var contentString=document.getElementById('description').value.toUpperCase();
	if(contentString.length!=0)
	{
		for(i in suggestions)
		{
			var count=0;
			var k;
			for(k=0;k<contentString.length;k++)
			{
				if(contentString.charAt(k)==suggestions[i].charAt(k))
				{	
					count++;
				}
				else
				{
					break;
				}
			}
			if(count==contentString.length)
			{
				buttondiv.innerHTML+="<button type=\"button\" class=\"btn btn-rounded btn-outline-warning btn-sm\" onclick=\" $('#description')[0].focus();$('#description')[0].value=\'"+defenitions[i]+"\' \" >"+suggestions[i]+"</button>";
			}
		}
	}
}