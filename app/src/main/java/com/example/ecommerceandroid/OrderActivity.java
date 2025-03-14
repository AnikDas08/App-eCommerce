package com.example.ecommerceandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.ecommerceandroid.databinding.ActivityOrderBinding;
import com.help5g.uddoktapaysdk.UddoktaPay;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

public class OrderActivity extends AppCompatActivity {

    ActivityOrderBinding binding;

    // Constants for payment
    private static final String API_KEY = "982d381360a69d419689740d9f2e26ce36fb7a50";
    private static final String CHECKOUT_URL = "https://sandbox.uddoktapay.com/api/checkout-v2";
    private static final String VERIFY_PAYMENT_URL = "https://sandbox.uddoktapay.com/api/verify-payment";
    private static final String REDIRECT_URL = "https://your-site.com";
    private static final String CANCEL_URL = "https://your-site.com";

    // Instance variables to store payment information
    private String storedFullName;
    private String storedEmail;
    private String storedAmount;
    private String storedInvoiceId;
    private String storedPaymentMethod;
    private String storedSenderNumber;
    private String storedTransactionId;
    private String storedDate;
    private String storedFee;
    private String storedChargedAmount;

    private String storedMetaKey1;
    private String storedMetaValue1;

    private String storedMetaKey2;
    private String storedMetaValue2;

    private String storedMetaKey3;
    private String storedMetaValue3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityOrderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String name=getIntent().getStringExtra("name");
        String details=getIntent().getStringExtra("details");
        String quantity=getIntent().getStringExtra("quantity");
        String prices=getIntent().getStringExtra("price");
        String image=getIntent().getStringExtra("image");
        int price=Integer.parseInt(prices);

        binding.NameText.setText(name);
        binding.QuantityText.setText(quantity);
        binding.DescriptionId.setText(prices);
        Glide.with(this)
                .load(image)
                .into(binding.ImageViewId);
        binding.Price.setText(prices);
        binding.PriceText.setText(prices);



        binding.ButtonId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogPlus dialog = DialogPlus.newDialog(OrderActivity.this)
                        .setContentHolder(new ViewHolder(R.layout.value_simple))
                        .setCancelable(true)
                        .create();
                View view1 = dialog.getHolderView();
                EditText name,email,payment;
                Button button;

                name=view1.findViewById(R.id.NameEdit);
                email=view1.findViewById(R.id.EmailEdit);
                payment=view1.findViewById(R.id.Payment);
                button=view1.findViewById(R.id.ButtonPay);

                payment.setText(prices);


                dialog.show();

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String names=name.getText().toString();
                        String emails=email.getText().toString();
                        String payments=payment.getText().toString();

                        LinearLayout uilayout,weblayout;
                        WebView webView;
                        webView=view1.findViewById(R.id.WebPay);
                        uilayout=view1.findViewById(R.id.UiLayout);
                        weblayout=view1.findViewById(R.id.WebViewLayout);

                        uilayout.setVisibility(View.GONE);
                        weblayout.setVisibility(View.VISIBLE);


                        // Set your metadata values in the map
                        Map<String, String> metadataMap = new HashMap<>();
                        metadataMap.put("CustomMetaData1", "Meta Value 1");
                        metadataMap.put("CustomMetaData2", "Meta Value 2");
                        metadataMap.put("CustomMetaData3", "Meta Value 3");

                        UddoktaPay.PaymentCallback paymentCallback = new UddoktaPay.PaymentCallback() {
                            @Override
                            public void onPaymentStatus(String status, String fullName, String email, String amount, String invoiceId,
                                                        String paymentMethod, String senderNumber, String transactionId,
                                                        String date, Map<String, String> metadataValues, String fee,String chargeAmount) {
                                // Callback method triggered when the payment status is received from the payment gateway.
                                // It provides information about the payment transaction.
                                storedFullName = names;
                                storedEmail = emails;
                                storedAmount = payments;
                                storedInvoiceId = invoiceId;
                                storedPaymentMethod = paymentMethod;
                                storedSenderNumber = senderNumber;
                                storedTransactionId = transactionId;
                                storedDate = date;
                                storedFee = fee;
                                storedChargedAmount = chargeAmount;

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        // Clear previous metadata values to avoid duplication
                                        storedMetaKey1 = null;
                                        storedMetaValue1 = null;
                                        storedMetaKey2 = null;
                                        storedMetaValue2 = null;
                                        storedMetaKey3 = null;
                                        storedMetaValue3 = null;

                                        // Iterate through the metadata map and store the key-value pairs
                                        for (Map.Entry<String, String> entry : metadataValues.entrySet()) {
                                            String metadataKey = entry.getKey();
                                            String metadataValue = entry.getValue();

                                            if ("CustomMetaData1".equals(metadataKey)) {
                                                storedMetaKey1 = metadataKey;
                                                storedMetaValue1 = metadataValue;
                                            } else if ("CustomMetaData2".equals(metadataKey)) {
                                                storedMetaKey2 = metadataKey;
                                                storedMetaValue2 = metadataValue;
                                            } else if ("CustomMetaData3".equals(metadataKey)) {
                                                storedMetaKey3 = metadataKey;
                                                storedMetaValue3 = metadataValue;
                                            }
                                        }

                                        // Update UI based on payment status
                                        if ("COMPLETED".equals(status)) {
                                            uilayout.setVisibility(View.VISIBLE);
                                            weblayout.setVisibility(View.GONE);
                                            Toast.makeText(OrderActivity.this, "Payment status: "+"Completed", Toast.LENGTH_SHORT).show();
                                            dialog.dismiss();
                                            finish();
                                        } else if ("PENDING".equals(status)) {
                                            uilayout.setVisibility(View.VISIBLE);
                                            weblayout.setVisibility(View.GONE);
                                            Toast.makeText(OrderActivity.this, "Payment status: "+"Panding", Toast.LENGTH_SHORT).show();
                                            finish();
                                        } else if ("ERROR".equals(status)) {
                                            // Handle payment error case
                                        }
                                    }
                                });
                            }
                        };

                        UddoktaPay uddoktapay = new UddoktaPay(webView, paymentCallback);
                        uddoktapay.loadPaymentForm(API_KEY, names, emails, payments, CHECKOUT_URL, VERIFY_PAYMENT_URL, REDIRECT_URL, CANCEL_URL, metadataMap);

                    }
                });

            }
        });











    }
}