function menuOn(imgEl)
{
	imgEl.src = imgEl.src.replace(".gif", "_on.gif");
 	imgEl.position='relative;'


}

function menuOut(imgEl)
{
	imgEl.src = imgEl.src.replace("_on.gif", ".gif");
	imgEl.position='static;'
}

function initTabMenu(tabContainerID) {
	var tabContainer = document.getElementById(tabContainerID);
	var tabAnchor = tabContainer.getElementsByTagName("a");
	var i = 0;

	for(i=0; i<tabAnchor.length; i++) {
		if (tabAnchor.item(i).className == "tabnav")
			thismenu = tabAnchor.item(i);
		else
			continue;

		thismenu.container = tabContainer;
		thismenu.targetEl = document.getElementById(tabAnchor.item(i).href.split("#")[1]);
		thismenu.targetEl.style.display = "none";
		thismenu.imgEl = thismenu.getElementsByTagName("img").item(0);
		thismenu.onclick = function tabMenuClick() {
			currentmenu = this.container.current;
			if (currentmenu == this)
				return false;

			if (currentmenu) {
				currentmenu.targetEl.style.display = "none";
				if (currentmenu.imgEl) {
					currentmenu.imgEl.src = currentmenu.imgEl.src.replace("_on.gif", ".gif");
				} else {
					currentmenu.className = currentmenu.className.replace(" on", "");
				}
			}
			this.targetEl.style.display = "";
			if (this.imgEl) {
				this.imgEl.src = this.imgEl.src.replace(".gif", "_on.gif");
			} else {
				this.className += " on";
			}
			this.container.current = this;

			return false;
		};

		if (!thismenu.container.first)
			thismenu.container.first = thismenu;
	}
	if (tabContainer.first)
		tabContainer.first.onclick();
}

function show(i)
{
	obj=document.getElementById('formSectionShow_'+i);
	
	obj1 = document.getElementById('formSection_'+i);
	if (obj)
		if (obj.style.display =="block")
			{		
			obj.style.display = "none";
			obj1.className = "formSectionTab_hidden";
			}
		else
			{
			obj.style.display = "block";
			obj1.className = "formSectionTab_show";
			}
		
}