package coloring.com.camera_coloring_book.model

class ARGB constructor() {
    var A : Int = 0
    var R : Int = 0
    var G : Int = 0
    var B : Int = 0

    constructor(a : Int, r : Int, g : Int, b : Int) : this(){
        this.A = a
        this.R = r
        this.G = g
        this.B = b
    }
}