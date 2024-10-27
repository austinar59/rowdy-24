		const ZWSP_TXT = "<span class=\"zwsp\">&#8288;</span>";
		const RIGHT_ARROW ='<div id="arrowLoc" class="right-arrow"></div>';
		const LEFT_ARROW ='<div id="arrowLoc" class="left-arrow"></div>';
		let currArrow = RIGHT_ARROW;
		var open = false;
		var instrList = {
			'instr1' : '🎹',
			'instr2' : 'guitar',
			'instr3' : 'v'
		}
		var names = {
			'🎹' : "Piano",
			'guitar' : 'Guitar',
			'v' : 'V'
		}
        var currentInst = '🎹';
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