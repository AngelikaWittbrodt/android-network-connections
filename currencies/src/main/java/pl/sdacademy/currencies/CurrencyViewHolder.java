package pl.sdacademy.currencies;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class CurrencyViewHolder extends RecyclerView.ViewHolder {

    private TextView symbolText;
    private TextView nameText;
    private TextView rateText;

    public CurrencyViewHolder(View itemView) {
        super(itemView);
        symbolText = (TextView) itemView.findViewById(R.id.symbol);
        nameText = (TextView) itemView.findViewById(R.id.currency);
        rateText = (TextView) itemView.findViewById(R.id.mid);
    }

    public void setSymbolText(String symbol) {
        symbolText.setText(symbol);
    }

    public void setNameText(String name) {
        nameText.setText(name);
    }

    public void setRateText(String rate) {
        rateText.setText(rate);
    }
}
