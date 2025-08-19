
    var student = {
        name: "David Rayy",
        sclass: "VI",
        rollNo: 12
    };

    for (var key in student) {
        // key will be "name", then "sclass", then "rollNo"
        console.log("Property name: " + key);
        console.log("Property value: " + student[key]);
    }

    var library = [ 
   {
       author: 'Bill Gates',
       title: 'The Road Ahead',
       readingStatus: true
   },
   {
       author: 'Steve Jobs',
       title: 'Walter Isaacson',
       readingStatus: true
   },
   {
       author: 'Suzanne Collins',
       title:  'Mockingjay: The Final Book of The Hunger Games', 
       readingStatus: false
   }];
  for (var i=0;i<library.length;i++){
    lb=library[i];
    console.log("author"+lb.author);
    console.log("title"+lb.title);
    console.log("reading status"+lb.readingStatus)
  }
  function removeValues(arr, valuesToRemove) {
    return arr.filter(function(item) {
        return !valuesToRemove.includes(item);
    });
}

// Example usage
const myArray = [1, 2, 3, 4, 5, 2, 3];
const valuesToRemove = [2, 3];

const result = removeValues(myArray, valuesToRemove);
console.log(result); // [1, 4, 5]
  