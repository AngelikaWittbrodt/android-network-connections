package pl.sdacademy.currencies;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

import pl.sdacademy.currencies.domain.CurrencyRate;

public class CurrencyAdapter extends RecyclerView.Adapter<CurrencyViewHolder> {

    private List<CurrencyRate> currencies = Collections.emptyList();

    @Override
    public CurrencyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_currency, parent, false);
        return new CurrencyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CurrencyViewHolder holder, int position) {
        CurrencyRate currencyRate = currencies.get(position);
        holder.setNameText(currencyRate.getCurrency());
        holder.setSymbolText(currencyRate.getCode());
        String rateText = String.valueOf(currencyRate.getMid());
        holder.setRateText(rateText);
    }

    @Override
    public int getItemCount() {
        return currencies.size();
    }

    public void setData(List<CurrencyRate> data) {
        this.currencies = data;
        notifyDataSetChanged();
    }
}
