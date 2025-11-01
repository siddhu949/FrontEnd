// Restaurant List Data
export const mockRestaurants = [
  {
    info: {
      id: "1",
      name: "Meghana Foods",
      cuisines: ["Biryani", "Andhra", "South Indian", "Chinese"],
      avgRating: 4.3,
      sla: { deliveryTime: 30 },
      costForTwo: "₹400 for two",
      cloudinaryImageId: "e0vvulfbahjxjz6k4uwi"
    }
  },
  {
    info: {
      id: "2",
      name: "KFC",
      cuisines: ["Burgers", "Fast Food", "Rolls & Wraps"],
      avgRating: 4.1,
      sla: { deliveryTime: 25 },
      costForTwo: "₹300 for two",
      cloudinaryImageId: "bdcd233971b7c81bf77e1fa4471280eb"
    }
  },
  {
    info: {
      id: "3",
      name: "Pizza Hut",
      cuisines: ["Pizzas", "Italian", "Pastas"],
      avgRating: 4.2,
      sla: { deliveryTime: 35 },
      costForTwo: "₹500 for two",
      cloudinaryImageId: "2b4f62d606d1b2bfba9ba9e5386fabb7"
    }
  },
  {
    info: {
      id: "4",
      name: "Domino's Pizza",
      cuisines: ["Pizzas", "Italian", "Fast Food", "Desserts"],
      avgRating: 4.0,
      sla: { deliveryTime: 30 },
      costForTwo: "₹400 for two",
      cloudinaryImageId: "bz9zkh2aqywjhpankb07"
    }
  },
  {
    info: {
      id: "5",
      name: "Burger King",
      cuisines: ["Burgers", "American", "Fast Food"],
      avgRating: 4.3,
      sla: { deliveryTime: 20 },
      costForTwo: "₹350 for two",
      cloudinaryImageId: "e33e1d3ba7d6b2bb0d45e1001b731fcf"
    }
  },
  {
    info: {
      id: "6",
      name: "Subway",
      cuisines: ["Healthy Food", "Salads", "Snacks", "Beverages"],
      avgRating: 4.1,
      sla: { deliveryTime: 28 },
      costForTwo: "₹300 for two",
      cloudinaryImageId: "1ace5fa65eff3e1223feb696c956b38b"
    }
  },
  {
    info: {
      id: "7",
      name: "Chinese Wok",
      cuisines: ["Chinese", "Asian", "Tibetan", "Thai"],
      avgRating: 4.2,
      sla: { deliveryTime: 32 },
      costForTwo: "₹350 for two",
      cloudinaryImageId: "e0839ff574213e6f35b3899ebf1fc597"
    }
  },
  {
    info: {
      id: "8",
      name: "Wow! Momo",
      cuisines: ["Tibetan", "Chinese", "Fast Food", "Momos"],
      avgRating: 4.4,
      sla: { deliveryTime: 25 },
      costForTwo: "₹250 for two",
      cloudinaryImageId: "0984acc0ed7b914206dbbcb90297becc"
    }
  },
  {
    info: {
      id: "9",
      name: "McDonald's",
      cuisines: ["Burgers", "Beverages", "Fast Food", "Desserts"],
      avgRating: 4.2,
      sla: { deliveryTime: 22 },
      costForTwo: "₹350 for two",
      cloudinaryImageId: "6dc3ed2ca21d3f03d4c0e9c6e5c9b47e"
    }
  },
  {
    info: {
      id: "10",
      name: "Starbucks Coffee",
      cuisines: ["Beverages", "Cafe", "Snacks", "Desserts"],
      avgRating: 4.5,
      sla: { deliveryTime: 28 },
      costForTwo: "₹500 for two",
      cloudinaryImageId: "258fa8abe5b8cc840232ab5f29e6e0f7"
    }
  },
  {
    info: {
      id: "11",
      name: "Biryani Blues",
      cuisines: ["Biryani", "Hyderabadi", "North Indian", "Kebabs"],
      avgRating: 4.3,
      sla: { deliveryTime: 35 },
      costForTwo: "₹450 for two",
      cloudinaryImageId: "97377e54963c50f2b4f59cd7e4f0b0d5"
    }
  },
  {
    info: {
      id: "12",
      name: "The Belgian Waffle Co.",
      cuisines: ["Desserts", "Waffles", "Ice Cream", "Beverages"],
      avgRating: 4.4,
      sla: { deliveryTime: 30 },
      costForTwo: "₹400 for two",
      cloudinaryImageId: "5f462f64cee7e6b1b89e44e328a7a45e"
    }
  }
];

// Menu Data for Each Restaurant
export const mockMenuData = {
  "1": {
    name: "Meghana Foods",
    cuisines: ["Biryani", "Andhra", "South Indian", "Chinese"],
    avgRating: 4.3,
    costForTwo: "₹400 for two",
    deliveryTime: 30,
    menuItems: [
      {
        id: "101",
        name: "Chicken Biryani",
        price: 250,
        description: "Authentic Hyderabadi style chicken biryani with raita",
        category: "Biryani",
        isVeg: false,
        imageId: "chicken-biryani"
      },
      {
        id: "102",
        name: "Mutton Biryani",
        price: 350,
        description: "Tender mutton pieces cooked with aromatic rice",
        category: "Biryani",
        isVeg: false,
        imageId: "mutton-biryani"
      },
      {
        id: "103",
        name: "Veg Biryani",
        price: 200,
        description: "Mixed vegetables with fragrant basmati rice",
        category: "Biryani",
        isVeg: true,
        imageId: "veg-biryani"
      },
      {
        id: "104",
        name: "Paneer Tikka",
        price: 180,
        description: "Grilled cottage cheese marinated in spices",
        category: "Starters",
        isVeg: true,
        imageId: "paneer-tikka"
      },
      {
        id: "105",
        name: "Chicken 65",
        price: 220,
        description: "Spicy fried chicken chunks",
        category: "Starters",
        isVeg: false,
        imageId: "chicken-65"
      },
      {
        id: "106",
        name: "Fish Fry",
        price: 280,
        description: "Crispy fried fish with spices",
        category: "Starters",
        isVeg: false,
        imageId: "fish-fry"
      }
    ]
  },
  "2": {
    name: "KFC",
    cuisines: ["Burgers", "Fast Food", "Rolls & Wraps"],
    avgRating: 4.1,
    costForTwo: "₹300 for two",
    deliveryTime: 25,
    menuItems: [
      {
        id: "201",
        name: "Zinger Burger",
        price: 199,
        description: "Crispy chicken fillet with spicy sauce",
        category: "Burgers",
        isVeg: false,
        imageId: "zinger-burger"
      },
      {
        id: "202",
        name: "Chicken Popcorn",
        price: 149,
        description: "Bite-sized crispy chicken pieces",
        category: "Snacks",
        isVeg: false,
        imageId: "chicken-popcorn"
      },
      {
        id: "203",
        name: "Veg Zinger",
        price: 179,
        description: "Crispy veg patty with lettuce and mayo",
        category: "Burgers",
        isVeg: true,
        imageId: "veg-zinger"
      },
      {
        id: "204",
        name: "Hot Wings",
        price: 189,
        description: "Spicy chicken wings - 6 pieces",
        category: "Snacks",
        isVeg: false,
        imageId: "hot-wings"
      },
      {
        id: "205",
        name: "Chicken Bucket",
        price: 449,
        description: "10 pieces of crispy fried chicken",
        category: "Buckets",
        isVeg: false,
        imageId: "chicken-bucket"
      },
      {
        id: "206",
        name: "Pepsi",
        price: 50,
        description: "Chilled Pepsi 330ml",
        category: "Beverages",
        isVeg: true,
        imageId: "pepsi"
      }
    ]
  },
  "3": {
    name: "Pizza Hut",
    cuisines: ["Pizzas", "Italian", "Pastas"],
    avgRating: 4.2,
    costForTwo: "₹500 for two",
    deliveryTime: 35,
    menuItems: [
      {
        id: "301",
        name: "Margherita Pizza",
        price: 299,
        description: "Classic cheese pizza with tomato sauce",
        category: "Pizza",
        isVeg: true,
        imageId: "margherita"
      },
      {
        id: "302",
        name: "Pepperoni Pizza",
        price: 399,
        description: "Loaded with pepperoni and cheese",
        category: "Pizza",
        isVeg: false,
        imageId: "pepperoni"
      },
      {
        id: "303",
        name: "Veggie Supreme Pizza",
        price: 349,
        description: "Mixed vegetables with cheese",
        category: "Pizza",
        isVeg: true,
        imageId: "veggie-supreme"
      },
      {
        id: "304",
        name: "Garlic Breadsticks",
        price: 149,
        description: "Freshly baked breadsticks with garlic butter",
        category: "Sides",
        isVeg: true,
        imageId: "garlic-bread"
      },
      {
        id: "305",
        name: "Pasta Alfredo",
        price: 249,
        description: "Creamy white sauce pasta",
        category: "Pasta",
        isVeg: true,
        imageId: "alfredo-pasta"
      },
      {
        id: "306",
        name: "Chocolate Lava Cake",
        price: 129,
        description: "Warm chocolate cake with molten center",
        category: "Desserts",
        isVeg: true,
        imageId: "lava-cake"
      }
    ]
  },
  "4": {
    name: "Domino's Pizza",
    cuisines: ["Pizzas", "Italian", "Fast Food", "Desserts"],
    avgRating: 4.0,
    costForTwo: "₹400 for two",
    deliveryTime: 30,
    menuItems: [
      {
        id: "401",
        name: "Farmhouse Pizza",
        price: 349,
        description: "Loaded with vegetables and cheese",
        category: "Pizza",
        isVeg: true,
        imageId: "farmhouse"
      },
      {
        id: "402",
        name: "Chicken Dominator",
        price: 499,
        description: "Triple chicken toppings",
        category: "Pizza",
        isVeg: false,
        imageId: "chicken-dominator"
      },
      {
        id: "403",
        name: "Cheese Burst Pizza",
        price: 399,
        description: "Cheese-filled crust pizza",
        category: "Pizza",
        isVeg: true,
        imageId: "cheese-burst"
      },
      {
        id: "404",
        name: "Garlic Breadsticks",
        price: 99,
        description: "Crispy garlic breadsticks",
        category: "Sides",
        isVeg: true,
        imageId: "breadsticks"
      },
      {
        id: "405",
        name: "Choco Lava Cake",
        price: 99,
        description: "Chocolate lava cake",
        category: "Desserts",
        isVeg: true,
        imageId: "choco-lava"
      }
    ]
  },
  "5": {
    name: "Burger King",
    cuisines: ["Burgers", "American", "Fast Food"],
    avgRating: 4.3,
    costForTwo: "₹350 for two",
    deliveryTime: 20,
    menuItems: [
      {
        id: "501",
        name: "Whopper",
        price: 199,
        description: "Flame-grilled beef patty with vegetables",
        category: "Burgers",
        isVeg: false,
        imageId: "whopper"
      },
      {
        id: "502",
        name: "Chicken Whopper",
        price: 189,
        description: "Grilled chicken patty with mayo",
        category: "Burgers",
        isVeg: false,
        imageId: "chicken-whopper"
      },
      {
        id: "503",
        name: "Veg Whopper",
        price: 149,
        description: "Veggie patty with fresh vegetables",
        category: "Burgers",
        isVeg: true,
        imageId: "veg-whopper"
      },
      {
        id: "504",
        name: "French Fries",
        price: 79,
        description: "Crispy golden fries",
        category: "Sides",
        isVeg: true,
        imageId: "fries"
      },
      {
        id: "505",
        name: "Onion Rings",
        price: 99,
        description: "Crispy battered onion rings",
        category: "Sides",
        isVeg: true,
        imageId: "onion-rings"
      }
    ]
  },
  "6": {
    name: "Subway",
    cuisines: ["Healthy Food", "Salads", "Snacks", "Beverages"],
    avgRating: 4.1,
    costForTwo: "₹300 for two",
    deliveryTime: 28,
    menuItems: [
      {
        id: "601",
        name: "Veg Delite Sub",
        price: 149,
        description: "Fresh vegetables with your choice of bread",
        category: "Subs",
        isVeg: true,
        imageId: "veg-delite"
      },
      {
        id: "602",
        name: "Chicken Teriyaki Sub",
        price: 219,
        description: "Grilled chicken with teriyaki sauce",
        category: "Subs",
        isVeg: false,
        imageId: "chicken-teriyaki"
      },
      {
        id: "603",
        name: "Paneer Tikka Sub",
        price: 199,
        description: "Tandoori paneer with vegetables",
        category: "Subs",
        isVeg: true,
        imageId: "paneer-tikka-sub"
      },
      {
        id: "604",
        name: "Veggie Salad",
        price: 129,
        description: "Fresh garden salad",
        category: "Salads",
        isVeg: true,
        imageId: "veggie-salad"
      },
      {
        id: "605",
        name: "Cookies",
        price: 49,
        description: "Freshly baked cookies",
        category: "Desserts",
        isVeg: true,
        imageId: "cookies"
      }
    ]
  },
  "7": {
    name: "Chinese Wok",
    cuisines: ["Chinese", "Asian", "Tibetan", "Thai"],
    avgRating: 4.2,
    costForTwo: "₹350 for two",
    deliveryTime: 32,
    menuItems: [
      {
        id: "701",
        name: "Veg Hakka Noodles",
        price: 180,
        description: "Stir-fried noodles with vegetables",
        category: "Noodles",
        isVeg: true,
        imageId: "hakka-noodles"
      },
      {
        id: "702",
        name: "Chicken Fried Rice",
        price: 220,
        description: "Fried rice with chicken pieces",
        category: "Rice",
        isVeg: false,
        imageId: "chicken-fried-rice"
      },
      {
        id: "703",
        name: "Veg Manchurian",
        price: 160,
        description: "Fried vegetable balls in spicy sauce",
        category: "Starters",
        isVeg: true,
        imageId: "veg-manchurian"
      },
      {
        id: "704",
        name: "Chilli Chicken",
        price: 240,
        description: "Spicy chicken in Indo-Chinese style",
        category: "Starters",
        isVeg: false,
        imageId: "chilli-chicken"
      },
      {
        id: "705",
        name: "Spring Rolls",
        price: 140,
        description: "Crispy vegetable spring rolls",
        category: "Starters",
        isVeg: true,
        imageId: "spring-rolls"
      }
    ]
  },
  "8": {
    name: "Wow! Momo",
    cuisines: ["Tibetan", "Chinese", "Fast Food", "Momos"],
    avgRating: 4.4,
    costForTwo: "₹250 for two",
    deliveryTime: 25,
    menuItems: [
      {
        id: "801",
        name: "Veg Steamed Momos",
        price: 120,
        description: "Steamed vegetable dumplings - 8 pieces",
        category: "Momos",
        isVeg: true,
        imageId: "veg-momos"
      },
      {
        id: "802",
        name: "Chicken Steamed Momos",
        price: 150,
        description: "Steamed chicken dumplings - 8 pieces",
        category: "Momos",
        isVeg: false,
        imageId: "chicken-momos"
      },
      {
        id: "803",
        name: "Veg Fried Momos",
        price: 140,
        description: "Crispy fried vegetable momos - 8 pieces",
        category: "Momos",
        isVeg: true,
        imageId: "veg-fried-momos"
      },
      {
        id: "804",
        name: "Chicken Fried Momos",
        price: 170,
        description: "Crispy fried chicken momos - 8 pieces",
        category: "Momos",
        isVeg: false,
        imageId: "chicken-fried-momos"
      },
      {
        id: "805",
        name: "Pan Fried Momos",
        price: 160,
        description: "Pan-fried momos with veggies",
        category: "Momos",
        isVeg: true,
        imageId: "pan-fried-momos"
      }
    ]
  },
  "9": {
    name: "McDonald's",
    cuisines: ["Burgers", "Beverages", "Fast Food", "Desserts"],
    avgRating: 4.2,
    costForTwo: "₹350 for two",
    deliveryTime: 22,
    menuItems: [
      {
        id: "901",
        name: "McVeggie Burger",
        price: 99,
        description: "Vegetable patty with lettuce and mayo",
        category: "Burgers",
        isVeg: true,
        imageId: "mcveggie"
      },
      {
        id: "902",
        name: "McChicken Burger",
        price: 129,
        description: "Crispy chicken patty burger",
        category: "Burgers",
        isVeg: false,
        imageId: "mcchicken"
      },
      {
        id: "903",
        name: "Big Mac",
        price: 199,
        description: "Double-decker burger with special sauce",
        category: "Burgers",
        isVeg: false,
        imageId: "big-mac"
      },
      {
        id: "904",
        name: "French Fries (M)",
        price: 79,
        description: "Medium crispy golden fries",
        category: "Sides",
        isVeg: true,
        imageId: "mcfries"
      },
      {
        id: "905",
        name: "McFlurry",
        price: 99,
        description: "Soft serve ice cream with toppings",
        category: "Desserts",
        isVeg: true,
        imageId: "mcflurry"
      },
      {
        id: "906",
        name: "Coke",
        price: 60,
        description: "Coca-Cola soft drink",
        category: "Beverages",
        isVeg: true,
        imageId: "coke"
      }
    ]
  },
  "10": {
    name: "Starbucks Coffee",
    cuisines: ["Beverages", "Cafe", "Snacks", "Desserts"],
    avgRating: 4.5,
    costForTwo: "₹500 for two",
    deliveryTime: 28,
    menuItems: [
      {
        id: "1001",
        name: "Caffe Latte",
        price: 295,
        description: "Espresso with steamed milk",
        category: "Beverages",
        isVeg: true,
        imageId: "latte"
      },
      {
        id: "1002",
        name: "Cappuccino",
        price: 275,
        description: "Espresso with foam milk",
        category: "Beverages",
        isVeg: true,
        imageId: "cappuccino"
      },
      {
        id: "1003",
        name: "Cold Coffee",
        price: 315,
        description: "Chilled coffee with ice",
        category: "Beverages",
        isVeg: true,
        imageId: "cold-coffee"
      },
      {
        id: "1004",
        name: "Chocolate Muffin",
        price: 180,
        description: "Rich chocolate muffin",
        category: "Desserts",
        isVeg: true,
        imageId: "choco-muffin"
      },
      {
        id: "1005",
        name: "Chicken Sandwich",
        price: 295,
        description: "Grilled chicken sandwich",
        category: "Snacks",
        isVeg: false,
        imageId: "chicken-sandwich"
      }
    ]
  },
  "11": {
    name: "Biryani Blues",
    cuisines: ["Biryani", "Hyderabadi", "North Indian", "Kebabs"],
    avgRating: 4.3,
    costForTwo: "₹450 for two",
    deliveryTime: 35,
    menuItems: [
      {
        id: "1101",
        name: "Hyderabadi Chicken Biryani",
        price: 299,
        description: "Authentic Hyderabadi chicken biryani",
        category: "Biryani",
        isVeg: false,
        imageId: "hyd-chicken-biryani"
      },
      {
        id: "1102",
        name: "Mutton Dum Biryani",
        price: 399,
        description: "Slow-cooked mutton biryani",
        category: "Biryani",
        isVeg: false,
        imageId: "mutton-dum-biryani"
      },
      {
        id: "1103",
        name: "Paneer Biryani",
        price: 249,
        description: "Aromatic paneer biryani",
        category: "Biryani",
        isVeg: true,
        imageId: "paneer-biryani"
      },
      {
        id: "1104",
        name: "Chicken Kebab",
        price: 220,
        description: "Grilled chicken kebabs",
        category: "Kebabs",
        isVeg: false,
        imageId: "chicken-kebab"
      },
      {
        id: "1105",
        name: "Raita",
        price: 50,
        description: "Cooling yogurt side dish",
        category: "Sides",
        isVeg: true,
        imageId: "raita"
      }
    ]
  },
  "12": {
    name: "The Belgian Waffle Co.",
    cuisines: ["Desserts", "Waffles", "Ice Cream", "Beverages"],
    avgRating: 4.4,
    costForTwo: "₹400 for two",
    deliveryTime: 30,
    menuItems: [
      {
        id: "1201",
        name: "Classic Belgian Waffle",
        price: 199,
        description: "Traditional Belgian waffle with maple syrup",
        category: "Waffles",
        isVeg: true,
        imageId: "belgian-waffle"
      },
      {
        id: "1202",
        name: "Chocolate Waffle",
        price: 249,
        description: "Waffle with chocolate sauce and ice cream",
        category: "Waffles",
        isVeg: true,
        imageId: "choco-waffle"
      },
      {
        id: "1203",
        name: "Nutella Waffle",
        price: 279,
        description: "Waffle topped with Nutella spread",
        category: "Waffles",
        isVeg: true,
        imageId: "nutella-waffle"
      },
      {
        id: "1204",
        name: "Vanilla Ice Cream",
        price: 129,
        description: "Creamy vanilla ice cream",
        category: "Ice Cream",
        isVeg: true,
        imageId: "vanilla-icecream"
      },
      {
        id: "1205",
        name: "Hot Chocolate",
        price: 149,
        description: "Rich hot chocolate drink",
        category: "Beverages",
        isVeg: true,
        imageId: "hot-chocolate"
      }
    ]
  }
};