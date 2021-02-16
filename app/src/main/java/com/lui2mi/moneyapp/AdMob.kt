package com.lui2mi.moneyapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.google.android.gms.ads.*
import com.google.android.gms.ads.reward.RewardItem
import com.google.android.gms.ads.reward.RewardedVideoAdListener
import java.util.*

class AdMob : AppCompatActivity() {
    val TAG = "AdMob"
    val BANNER = "BANNER "
    val INTERSTITIAL ="INTERSTITIAL "
    val REWARDED ="REWARDED "
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ad_mob)

        MobileAds.initialize(this,"ca-app-pub-3940256099942544~3347511713")
        val configuration = RequestConfiguration.Builder().setTestDeviceIds(
            Arrays.asList("01D58979AA8DC2921EF9E91CC2FDAD22")).build()
        MobileAds.setRequestConfiguration(configuration)
        // Interstitial
        val interstitialAd = InterstitialAd(this)
        // prueba ca-app-pub-3940256099942544/1033173712
        // prduct ca-app-pub-6931890428485350/4971127018
        interstitialAd.adUnitId = "ca-app-pub-3940256099942544/1033173712"
        interstitialAd.loadAd(AdRequest.Builder().build())
        interstitialAd.adListener = vInterstitialListener
        findViewById<Button>(R.id.btn_interstitial).setOnClickListener {
            if(interstitialAd.isLoaded){
                interstitialAd.show()
            } else {
                Toast.makeText(this,"No disponible", Toast.LENGTH_LONG).show()
            }
        }
        // RewardedVideo
        val vRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this)
        vRewardedVideoAd.rewardedVideoAdListener = vRewardedVideoListener
        findViewById<Button>(R.id.btn_rewardedvideo).setOnClickListener {
            // prueba ca-app-pub-3940256099942544/5224354917
            if (vRewardedVideoAd.isLoaded) {
                vRewardedVideoAd.loadAd("ca-app-pub-3940256099942544/5224354917",
                    AdRequest.Builder().build())
            } else {
                Toast.makeText(this,"No disponible", Toast.LENGTH_LONG).show()
            }
        }
        // Banner
        val banner = findViewById<AdView>(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        banner.loadAd(adRequest)
        banner.adListener = vBannerListener
    }

    val vBannerListener = object: AdListener() {
        override fun onAdClosed() {
            Log.e(TAG,BANNER+"onAdClosed")
            super.onAdClosed()
        }

        override fun onAdFailedToLoad(p0: Int) {
            Log.e(TAG,BANNER+"onAdFailedToLoad")
            super.onAdFailedToLoad(p0)
        }

        override fun onAdFailedToLoad(p0: LoadAdError?) {
            Log.e(TAG,BANNER+"onAdFailedToLoad")
            super.onAdFailedToLoad(p0)
        }

        override fun onAdLeftApplication() {
            Log.e(TAG,BANNER+"onAdLeftApplication")
            super.onAdLeftApplication()
        }

        override fun onAdOpened() {
            Log.e(TAG,BANNER+"onAdOpened")
            super.onAdOpened()
        }

        override fun onAdLoaded() {
            Log.e(TAG,BANNER+"onAdLoaded")
            super.onAdLoaded()
        }

        override fun onAdClicked() {
            Log.e(TAG,BANNER+"onAdClicked")
            super.onAdClicked()
        }

        override fun onAdImpression() {
            Log.e(TAG,BANNER+"onAdImpression")
            super.onAdImpression()
        }
    }
    val vInterstitialListener = object: AdListener() {
        override fun onAdLoaded() {
            Log.e(TAG,INTERSTITIAL+"onAdLoaded")
        }

        override fun onAdFailedToLoad(errorCode: Int) {
            Log.e(TAG,INTERSTITIAL+"onAdFailedToLoad")
        }

        override fun onAdOpened() {
            Log.e(TAG,INTERSTITIAL+"onAdOpened")
        }

        override fun onAdClicked() {
            Log.e(TAG,INTERSTITIAL+"onAdClicked")
        }

        override fun onAdLeftApplication() {
            Log.e(TAG,INTERSTITIAL+"onAdLeftApplication")
        }

        override fun onAdClosed() {
            Log.e(TAG,INTERSTITIAL+"onAdClosed")
        }
    }
    val vRewardedVideoListener = object: RewardedVideoAdListener{
        override fun onRewardedVideoAdLoaded() {
            Log.e(TAG,REWARDED+"onRewardedVideoAdLoaded")
        }

        override fun onRewardedVideoAdOpened() {
            Log.e(TAG,REWARDED+"onRewardedVideoAdOpened")
        }

        override fun onRewardedVideoStarted() {
            Log.e(TAG,REWARDED+"onRewardedVideoStarted")
        }

        override fun onRewardedVideoAdClosed() {
            Log.e(TAG,REWARDED+"onRewardedVideoAdClosed")
        }

        override fun onRewarded(p0: RewardItem?) {
            Log.e(TAG,REWARDED+"onRewarded")
        }

        override fun onRewardedVideoAdLeftApplication() {
            Log.e(TAG,REWARDED+"onRewardedVideoAdLeftApplication")
        }

        override fun onRewardedVideoAdFailedToLoad(p0: Int) {
            Log.e(TAG,REWARDED+"onRewardedVideoAdFailedToLoad")
        }

        override fun onRewardedVideoCompleted() {
            Log.e(TAG,REWARDED+"onRewardedVideoCompleted")
        }
    }
}