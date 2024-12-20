		//constant variables to be used in manipulating html of index, and currArrow to store arrow value
		const ZWSP_TXT = "<span class=\"zwsp\">&#8288;</span>";
		const RIGHT_ARROW ='<div id="arrowLoc" class="right-arrow"></div>';
		const LEFT_ARROW ='<div id="arrowLoc" class="left-arrow"></div>';
		let currArrow = RIGHT_ARROW;
		//openness of menu storage and eventlistener for keypresses
		var open = false;
		document.addEventListener("keydown", keyPress);
		//list for translation from css ids to emojis for middle- and frontend use
		var instrList = {
			'instr1' : '🎛️',
			'instr2' : '🎸',
			'instr3' : '🎹'
		}
		//list for names for java-end use
		var names = {
			'🎛️' : "synth",
			'🎸' : 'pluck',
			'🎹' : 'piano'
		}
		//stores current instrument, initialized to default selection
        var currentInst = '🎛️';
		//takes keypress and translates to note name
		function keyPress(k) {
			let keyDict = {
				'A' : 'C',
				'a' : 'C',
				'S' : 'D',
				's' : 'D',
				'D' : 'E',
				'd' : 'E',
				'F' : 'F',
				'f' : 'F',
				'G' : 'G',
				'g' : 'G',
				'H' : 'A',
				'h' : 'A',
				'J' : 'B',
				'j' : 'B',
				'K' : 'C2',
				'k' : 'C2'
			}
			let x = keyDict[k.key];
			callJava(names[currentInst], x);
		}
		//switches instrument order in html
		function switchInstrument(input, button)
		{
				let x = instrList[input];
				let y = currentInst;
				currentInst = x;
				instrList[instr1] = x;
				instrList[input] = y;
				document.getElementById("instr1").innerHTML = ZWSP_TXT + instrList[instr1] + currArrow;
				document.getElementById(input).innerHTML = ZWSP_TXT + instrList[input];
		}
		//opens and closes list
		function listChange(button){
				switch(open)
				{
					case true:
						document.getElementById("instr2").className = "instr hidden";
						document.getElementById("instr3").className = "instr hidden";
						open = false;
						currArrow = RIGHT_ARROW;
						break;
					case false:
						document.getElementById("instr2").className = "instr";
						document.getElementById("instr3").className = "instr";
						open = true;
						currArrow = LEFT_ARROW;
						break;
				}
				document.getElementById("arrowLoc").outerHTML = currArrow;
		}
		//calls springboot backend
		function callJava(instrument, note) {
		//console.log(instrument, note);
        const message = `${instrument}-${note}`;  // Format as "Instrument-Note"

        fetch('/sendData', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ message })  // Send single formatted string
        })
        .then(response => response.text())
        .then(data => {
            console.log(data); // Display response from API
        })
        .catch(error => console.error('Error:', error));
    }