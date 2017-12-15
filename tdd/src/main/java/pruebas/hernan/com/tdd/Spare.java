package pruebas.hernan.com.tdd;

class Spare extends Frame implements FrameVisitable {

    Spare(Shot... shots) {
        super(shots);
    }

    @Override
    public void visit(FrameVisitor visitor) {
        visitor.accept(this);
    }
}
