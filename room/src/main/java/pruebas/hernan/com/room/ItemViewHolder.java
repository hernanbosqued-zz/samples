package pruebas.hernan.com.room;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;


class ItemViewHolder extends RecyclerView.ViewHolder {

    @BindView(android.R.id.text1)
    TextView text;

    public ItemViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    public void bind( String value ){
        text.setText(value);
    }
}
