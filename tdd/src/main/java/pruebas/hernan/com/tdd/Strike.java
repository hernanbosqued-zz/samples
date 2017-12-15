package pruebas.hernan.com.tdd;

class Strike extends Frame implements FrameVisitable{

    Strike() {
        super(new Shot(10));
    }

    @Override
    public void visit(FrameVisitor visitor) {
        visitor.accept(this);
    }
}
