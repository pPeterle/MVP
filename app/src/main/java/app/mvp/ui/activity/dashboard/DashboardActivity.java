package app.mvp.ui.activity.dashboard;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.zxing.EncodeHintType;

import net.glxn.qrgen.android.QRCode;

import java.util.ArrayList;

import app.mvp.R;
import app.mvp.adapter.EstablishmentsAdapter;
import app.mvp.model.Establishment;
import app.mvp.model.User;
import app.mvp.retrofit.Config;
import app.mvp.service.EstablishmentService;
import app.mvp.session.Session;
import app.mvp.ui.activity.home.HomeActivity;
import app.mvp.ui.activity.profile.ProfileActivity;
import retrofit2.Call;
import retrofit2.Callback;

public class DashboardActivity extends AppCompatActivity {
    public User user;

    private Handler handler;
    private Intent intent;
    private EstablishmentService establishmentService;
    private Toolbar toolbar;
    private ImageView imageView_qrcode, imageView_search;
    private TextView textView_nickname;
    private TextInputEditText et_search;
    private CardView cardView_suggestions;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        setSupportActionBar(toolbar);

        handler = new Handler();
        user = new User();
        dialog = new Dialog(this);
        establishmentService = Config.getEstablishmentService();

        toolbar = findViewById(R.id.toolbar);
        textView_nickname = findViewById(R.id.textView_nickname);
        et_search = findViewById(R.id.et_search);
        cardView_suggestions = findViewById(R.id.cardView_suggestions);
        imageView_qrcode = findViewById(R.id.imageView_qrcode);

        qrCode();
        onClick();
        animation();
    }

    private void qrCode() {
        try {
            Bitmap bitmap = QRCode.from("52740a30-35bc-40eb-a718-14cec5195409")
                    .withHint(EncodeHintType.MARGIN, 0)
                    .withColor(0xFFE64A19, 0xFFFFFFFF).bitmap();
            imageView_qrcode.setImageBitmap(bitmap);
        } catch(Exception e) {
            Log.e("QRCode Error: ", e.getMessage());
        }
    }

    private void onClick() {
        imageView_search = findViewById(R.id.imageView_search);
        imageView_search.setOnClickListener(search);

        CardView cardView_profile = findViewById(R.id.cardView_profile);
        cardView_profile.setOnClickListener(profile);

        CardView cardView_orders = findViewById(R.id.cardView_orders);
        cardView_orders.setOnClickListener(orders);

        CardView cardView_ranking = findViewById(R.id.cardView_ranking);
        cardView_ranking.setOnClickListener(ranking);

        CardView cardView_historic = findViewById(R.id.cardView_historic);
        cardView_historic.setOnClickListener(historic);

        CardView cardView_config = findViewById(R.id.cardView_config);
        cardView_config.setOnClickListener(config);

        CardView cardView_logout = findViewById(R.id.cardView_logout);
        cardView_logout.setOnClickListener(logout);
    }

    private void animation() {
        RelativeLayout relativeLayout_toolbar = findViewById(R.id.relativeLayout_toolbar);
        final RelativeLayout relativeLayout_horizontal_scrollview = findViewById(R.id.relativeLayout_horizontal_scrollview);

        Animation fade_in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
        relativeLayout_toolbar.startAnimation(fade_in);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        imageView_qrcode.setVisibility(View.VISIBLE);
                        Animation overshoot = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.overshoot);
                        imageView_qrcode.startAnimation(overshoot);
                    }
                });
            }
        }, 320);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        relativeLayout_horizontal_scrollview.setVisibility(View.VISIBLE);
                        Animation overshoot = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.overshoot);
                        relativeLayout_horizontal_scrollview.startAnimation(overshoot);
                    }
                });
            }
        }, 200);
    }

    private View.OnClickListener search = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            cardView_suggestions.setVisibility(View.VISIBLE);
            et_search.setVisibility(View.VISIBLE);

            textView_nickname.setVisibility(View.GONE);
            imageView_search.setVisibility(View.GONE);

            et_search.setHint(Html.fromHtml("<small><small>" + getString(R.string.search) + "</small></small>"));
            et_search.setFocusable(true);
            et_search.requestFocus();
            et_search.addTextChangedListener(input);

            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.showSoftInput(et_search, InputMethodManager.SHOW_IMPLICIT);
            }

            Establishment establishment = new Establishment();
            establishment.setName("Lojas Americanas");
            establishment.setCity("São Paulo, SP");

            ArrayList<Establishment> array = new ArrayList<>();
            array.add(establishment);

            RecyclerView recyclerView = findViewById(R.id.recyclerView);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            RecyclerView.Adapter adapter = new EstablishmentsAdapter(array);
            recyclerView.setAdapter(adapter);

            /*adapter.setOnItemClickListener(new ItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    ToastHelper.alert("Elemento" + position + " clicado", DashboardActivity.this);
                }
            });*/

            Animation fade_in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in_suggestions);
            Animation slide_up = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.up_suggestions);

            AnimationSet animationSet = new AnimationSet(false);
            animationSet.addAnimation(fade_in);
            animationSet.addAnimation(slide_up);
            cardView_suggestions.startAnimation(animationSet);

            Animation overshoot = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.overshoot_recyclerview);
            recyclerView.startAnimation(overshoot);
        }
    };

    private TextWatcher input = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence cs, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence cs, int start, int before, int count) {
            if (cs.length() >= 3) {
                searchEstablishment(cs.toString().trim());
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {}
    };

    private void searchEstablishment(String name) {
        Call<Establishment> response = establishmentService.search(name);

        response.enqueue(new Callback<Establishment>() {
            @Override
            public void onResponse(@NonNull Call<Establishment> call, @NonNull retrofit2.Response<Establishment> response) {
                final Establishment resp = response.body();

                if (resp != null) {
                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            System.out.println("Response: " + resp);
                        }
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<Establishment> call, @NonNull Throwable t) {}
        });
    }

    private View.OnClickListener profile = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            intent = new Intent(DashboardActivity.this, ProfileActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.right_to_left, R.anim.stable);
        }
    };

    private View.OnClickListener orders = new View.OnClickListener() {
        @Override
        public void onClick(View view) {}
    };

    private View.OnClickListener ranking = new View.OnClickListener() {
        @Override
        public void onClick(View view) {}
    };

    private View.OnClickListener historic = new View.OnClickListener() {
        @Override
        public void onClick(View view) {}
    };

    private View.OnClickListener config = new View.OnClickListener() {
        @Override
        public void onClick(View view) {}
    };

    private View.OnClickListener logout = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            logoutDialog();
        }
    };

    private void logoutDialog() {
        dialog.setContentView(R.layout.dialog_logout);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

        TextView cancelBtn = dialog.findViewById(R.id.textView_cancel);
        TextView logoutBtn = dialog.findViewById(R.id.textView_logout);

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Session session = new Session(getApplicationContext());
                session.logout();

                intent = new Intent(DashboardActivity.this, HomeActivity.class);
                startActivity(intent);
                DashboardActivity.this.finish();
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    @Override
    public void onBackPressed() {
        handler.removeCallbacksAndMessages(null);

        if (et_search.getVisibility() == View.GONE) {
            super.onBackPressed();
        }

        if (cardView_suggestions.getVisibility() == View.VISIBLE) {
            Animation fade_out = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out_suggestions);
            Animation slide_down = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.down_suggestions);

            AnimationSet animationSet = new AnimationSet(false);
            animationSet.addAnimation(fade_out);
            animationSet.addAnimation(slide_down);
            cardView_suggestions.startAnimation(animationSet);

            et_search.setVisibility(View.GONE);
            textView_nickname.setVisibility(View.VISIBLE);
            imageView_search.setVisibility(View.VISIBLE);

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            cardView_suggestions.setVisibility(View.GONE);
                        }
                    });
                }
            }, 200);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
    }
}
