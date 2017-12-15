package pruebas.hernan.com.tdd;


interface FrameVisitor {
    void accept(Spare spare);
    void accept(Strike strike);
    void accept(Frame frame);
}
