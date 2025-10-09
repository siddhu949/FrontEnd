// Create the entire page dynamically with JS
document.body.style.margin = "0";
document.body.style.fontFamily = "Arial, sans-serif";
document.body.style.backgroundColor = "#121212";
document.body.style.color = "#fff";
document.body.style.scrollBehavior = "smooth";

// Data
const nowShowing = [
    {title: "Spider-Man: No Way Home", img: "https://tse2.mm.bing.net/th/id/OIP.jFWga73Vye9w2gQvqWJX6gHaLH?cb=12&rs=1&pid=ImgDetMain&o=7&rm=3", desc:"Action, 2h 28m"},
    {title: "Avatar: The Way of Water", img: "https://tse4.mm.bing.net/th/id/OIP.73SG9-j2EMH3k-ekDwfqzwHaLH?cb=12&rs=1&pid=ImgDetMain&o=7&rm=3", desc:"Adventure, 3h 12m"},
    {title: "Black Panther: Wakanda Forever", img: "https://th.bing.com/th/id/OIP.rnMklcBwV1xZl5ndF5sTSAHaK-?w=205&h=304&c=7&r=0&o=7&cb=12&dpr=1.3&pid=1.7&rm=3", desc:"Action, 2h 41m"},
    {title: "The Batman", img: "https://th.bing.com/th/id/OIP.ORrLW2ur-82K0mQ2QeH1jgHaK-?w=117&h=180&c=7&r=0&o=7&cb=12&dpr=1.3&pid=1.7&rm=3", desc:"Crime, 2h 56m"},
    {title: "Doctor Strange: Multiverse of Madness", img: "https://th.bing.com/th/id/OIP.n7o8B0-d6P4-r3150OWN5AHaK9?w=205&h=304&c=7&r=0&o=7&cb=12&dpr=1.3&pid=1.7&rm=3", desc:"Fantasy, 2h 6m"}
];

const comingSoon = [
    {title:"Jurassic World: Dominion", img:"https://th.bing.com/th/id/OIP.NsVJ8Tg22sXq3mjtsaTowgHaKe?w=117&h=180&c=7&r=0&o=7&cb=12&dpr=1.3&pid=1.7&rm=3", desc:"Adventure, 2h 27m"},
    {title:"Guardians of the Galaxy Vol.3", img:"https://th.bing.com/th/id/OIP.4E32Anj4RLBR4T3KZGh9cgHaLH?w=204&h=306&c=7&r=0&o=7&cb=12&dpr=1.3&pid=1.7&rm=3", desc:"Action, 2h 30m"},
    {title:"Mission Impossible: Dead Reckoning", img:"https://th.bing.com/th/id/OIP.qrICVBnT6qb3GAyU2HdDkwHaJv?w=128&h=180&c=7&r=0&o=7&cb=12&dpr=1.3&pid=1.7&rm=3", desc:"Action, 2h 25m"},
    {title:"The Flash", img:"https://th.bing.com/th/id/OIP.lXzCvrnInawFeoJW9JUIBAHaLH?w=115&h=180&c=7&r=0&o=7&cb=12&dpr=1.3&pid=1.7&rm=3", desc:"Action, 2h 20m"},
    {title:"Ant-Man and the Wasp: Quantumania", img:"https://th.bing.com/th/id/OIP.0LOcwcHglyOwFYhBgBwoFQHaK-?w=125&h=185&c=7&r=0&o=7&cb=12&dpr=1.3&pid=1.7&rm=3", desc:"Adventure, 2h 5m"}
];

// Helper to create elements
function createElement(tag, parent, options = {}) {
    const el = document.createElement(tag);
    if(options.class) el.className = options.class;
    if(options.text) el.textContent = options.text;
    if(options.src) el.src = options.src;
    if(options.href) el.href = options.href;
    if(options.alt) el.alt = options.alt;
    if(options.style) Object.assign(el.style, options.style);
    parent.appendChild(el);
    return el;
}

// Navbar
const navbar = createElement('nav', document.body, {style: {
    display: "flex",
    justifyContent: "space-between",
    alignItems: "center",
    padding: "15px 5%",
    backgroundColor: "#1f1f1f",
    flexWrap: "wrap"
}});
createElement('h1', navbar, {text: "MovieBook", style:{color:"#ff3d00", fontSize:"1.5rem"}});

const ul = createElement('ul', navbar, {style:{listStyle:"none", display:"flex", gap:"15px", flexWrap:"wrap"}});
["Now Showing","#now-showing","Coming Soon","#coming-soon","Profile","javascript:void(0)"].reduce((_, text, i)=>{
    if(i%2===0){
        const li = createElement('li', ul);
        createElement('a', li, {text:text, href:["#now-showing","#coming-soon","javascript:void(0)"][i/2]})
    }
},0);

// Function to create a section dynamically
function createSection(title, movies, id){
    const section = createElement('div', document.body, {style:{padding:"30px 5%", textAlign:"center"}, class:"section"});
    section.id = id;
    createElement('h2', section, {text:title, style:{color:"#ff3d00", marginBottom:"25px"}});
    const container = createElement('div', section, {style:{
        display:"flex",
        gap:"25px",
        overflowX:"auto",
        paddingBottom:"10px",
        justifyContent:"center"
    }, id: id + "-movies"});

    movies.forEach(movie => {
        const card = createElement('div', container, {style:{
            flex:"0 0 280px",
            backgroundColor:"#1f1f1f",
            borderRadius:"10px",
            overflow:"hidden",
            cursor:"pointer",
            display:"flex",
            flexDirection:"column",
            transition:"transform 0.3s"
        }, class:"movie-card"});
        card.onmouseover = ()=> card.style.transform = "scale(1.05)";
        card.onmouseleave = ()=> card.style.transform = "scale(1)";

        createElement('img', card, {src:movie.img, alt:movie.title, style:{width:"100%", height:"300px", objectFit:"cover"}});
        const info = createElement('div', card, {style:{padding:"8px", flex:"1"}});
        createElement('h3', info, {text:movie.title, style:{fontSize:"1rem", marginBottom:"3px"}});
        createElement('p', info, {text:movie.desc, style:{fontSize:"0.8rem", color:"#bbb"}});
    });
}

// Create sections dynamically
createSection("Now Showing", nowShowing, "now-showing");
createSection("Coming Soon", comingSoon, "coming-soon");

// Responsive (resize movie-card width)
window.addEventListener('resize', ()=> {
    const cards = document.querySelectorAll('.movie-card');
    const w = window.innerWidth;
    cards.forEach(c=>{
        if(w<=480) c.style.flex = "0 0 120px";
        else if(w<=768) c.style.flex = "0 0 150px";
        else c.style.flex = "0 0 280px";
    });
});
