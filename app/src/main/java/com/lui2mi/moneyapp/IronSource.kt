package com.lui2mi.moneyapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.FrameLayout
import android.widget.Toast
import com.ironsource.mediationsdk.ISBannerSize
import com.ironsource.mediationsdk.IronSource
import com.ironsource.mediationsdk.integration.IntegrationHelper
import com.ironsource.mediationsdk.logger.IronSourceError
import com.ironsource.mediationsdk.model.Placement
import com.ironsource.mediationsdk.sdk.BannerListener
import com.ironsource.mediationsdk.sdk.InterstitialListener
import com.ironsource.mediationsdk.sdk.OfferwallListener
import com.ironsource.mediationsdk.sdk.RewardedVideoListener

class IronSource : AppCompatActivity() {
    val appKey = "eb921f21"
    val TAG = "IronSource"
    var offerwallAvailable = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_iron_source)
        IronSource.init(this,appKey)

        IronSource.init(this, appKey, IronSource.AD_UNIT.REWARDED_VIDEO)
        IronSource.init(this, appKey, IronSource.AD_UNIT.INTERSTITIAL)
        IronSource.init(this, appKey, IronSource.AD_UNIT.OFFERWALL)
        IronSource.init(this, appKey, IronSource.AD_UNIT.BANNER)

        IronSource.setRewardedVideoListener(vRewardedVideoListener)
        IronSource.setInterstitialListener(vInterstitialListener)
        IronSource.setOfferwallListener(vOfferwallListener)

        IronSource.loadInterstitial();

        findViewById<Button>(R.id.btn_rewardvideo).setOnClickListener {
            if(IronSource.isRewardedVideoAvailable()){
                IronSource.showRewardedVideo("Main_Menu");
            } else {
                Toast.makeText(this,"No disponible",Toast.LENGTH_LONG).show()
            }
        }
        findViewById<Button>(R.id.btn_interstitial).setOnClickListener {
            val isInterstitialReady = IronSource.isInterstitialReady()
            val isInterstitialPlacementCapped = IronSource.isInterstitialPlacementCapped("DefaultInterstitial")
            if(isInterstitialReady || isInterstitialPlacementCapped){
                IronSource.showInterstitial("DefaultInterstitial")
            } else {
                Toast.makeText(this,"No disponible",Toast.LENGTH_LONG).show()
            }
        }
        findViewById<Button>(R.id.btn_offerwall).setOnClickListener {
            if(offerwallAvailable){
                IronSource.showOfferwall("DefaultOfferWall");
            } else {
                Toast.makeText(this,"No disponible",Toast.LENGTH_LONG).show()
            }
        }
        val bannerContainer = findViewById<FrameLayout>(R.id.bannerContainer)
        val banner = IronSource.createBanner(this, ISBannerSize.SMART)
        val layoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.WRAP_CONTENT)
        bannerContainer.addView(banner,0, layoutParams)
        banner.bannerListener = vBannerListener
        IronSource.loadBanner(banner)
        IntegrationHelper.validateIntegration(this);
    }

    override fun onResume() {
        super.onResume()
        IronSource.onResume(this)
    }

    override fun onPause() {
        super.onPause()
        IronSource.onPause(this)
    }

    val vRewardedVideoListener = object: RewardedVideoListener{
        override fun onRewardedVideoAdOpened() {
            Log.e(TAG, "onRewardedVideoAdOpened")
        }

        override fun onRewardedVideoAdClosed() {
            Log.e(TAG, "onRewardedVideoAdClosed")
        }

        override fun onRewardedVideoAvailabilityChanged(p0: Boolean) {
            Log.e(TAG, "onRewardedVideoAvailabilityChanged")
        }

        override fun onRewardedVideoAdStarted() {
            Log.e(TAG, "onRewardedVideoAdStarted")
        }

        override fun onRewardedVideoAdEnded() {
            Log.e(TAG, "onRewardedVideoAdEnded")
        }

        override fun onRewardedVideoAdRewarded(p0: Placement?) {
            Log.e(TAG, "onRewardedVideoAdRewarded")
        }

        override fun onRewardedVideoAdShowFailed(p0: IronSourceError?) {
            Log.e(TAG, "onRewardedVideoAdShowFailed")
        }

        override fun onRewardedVideoAdClicked(p0: Placement?) {
            Log.e(TAG, "onRewardedVideoAdClicked")
        }
    }
    val vInterstitialListener = object : InterstitialListener{
        override fun onInterstitialAdReady() {
            Log.e(TAG, "onInterstitialAdReady")
        }

        override fun onInterstitialAdLoadFailed(p0: IronSourceError?) {
            Log.e(TAG, "onInterstitialAdLoadFailed")
        }

        override fun onInterstitialAdOpened() {
            Log.e(TAG, "onInterstitialAdOpened")
        }

        override fun onInterstitialAdClosed() {
            Log.e(TAG, "onInterstitialAdClosed")
        }

        override fun onInterstitialAdShowSucceeded() {
            Log.e(TAG, "onInterstitialAdShowSucceeded")
        }

        override fun onInterstitialAdShowFailed(p0: IronSourceError?) {
            Log.e(TAG, "onInterstitialAdShowFailed")
        }

        override fun onInterstitialAdClicked() {
            Log.e(TAG, "onInterstitialAdClicked")
        }
    }
    val vOfferwallListener = object : OfferwallListener{
        override fun onOfferwallAvailable(p0: Boolean) {
            Log.e(TAG, "onOfferwallAvailable")
            offerwallAvailable = p0
        }

        override fun onOfferwallOpened() {
            Log.e(TAG, "onOfferwallOpened")
        }

        override fun onOfferwallShowFailed(p0: IronSourceError?) {
            Log.e(TAG, "onOfferwallShowFailed")
        }

        override fun onOfferwallAdCredited(p0: Int, p1: Int, p2: Boolean): Boolean {
            Log.e(TAG, "onOfferwallAdCredited")
            return true
        }

        override fun onGetOfferwallCreditsFailed(p0: IronSourceError?) {
            Log.e(TAG, "onGetOfferwallCreditsFailed")
        }

        override fun onOfferwallClosed() {
            Log.e(TAG, "onOfferwallClosed")
        }
    }
    val vBannerListener = object : BannerListener{
        override fun onBannerAdLoaded() {
            Log.e(TAG, "onBannerAdLoaded")
        }

        override fun onBannerAdLoadFailed(p0: IronSourceError?) {
            Log.e(TAG, "onBannerAdLoadFailed")
        }

        override fun onBannerAdClicked() {
            Log.e(TAG, "onBannerAdClicked")
        }

        override fun onBannerAdScreenPresented() {
            Log.e(TAG, "onBannerAdScreenPresented")
        }

        override fun onBannerAdScreenDismissed() {
            Log.e(TAG, "onBannerAdScreenDismissed")
        }

        override fun onBannerAdLeftApplication() {
            Log.e(TAG, "onBannerAdLeftApplication")
        }
    }
}