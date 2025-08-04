let events=[
    "click",
    "dblclick",
    "mouseover",
    "mouseout",
    "mousedown",
    "mouseup",
    "keydown",
    "keyup",
    "wheel"
];
var button=document.getElementById("btn");
// button.addEventListener("cilck",function(e){
//     console.log(e.type);
// });
function handleEvent(event){
    console.log(event.type);
}
//for each event in the events array
events.forEach(function(eventType){
    button.addEventListener(eventType,handleEvent);
});