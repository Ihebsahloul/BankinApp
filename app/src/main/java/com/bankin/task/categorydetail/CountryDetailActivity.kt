package com.bankin.task.categorydetail

import android.net.Uri
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import com.bankin.domain.countries.model.Country
import com.bankin.task.R
import com.bankin.task.BankinApp
import com.bankin.task.categorydetail.di.component.DaggerCountryDetailComponent
import com.bankin.task.mvp.CleanActivity
import kotlinx.android.synthetic.main.activity_countrydetail.*


class CountryDetailActivity : CleanActivity <CountryDetailPresenter>(), CountryDetailView {
    override fun getLayout(): Int = R.layout.activity_countrydetail
    val stootieApp = BankinApp()

    override fun initInjector() {
      DaggerCountryDetailComponent.builder()
          .appComponent(stootieApp.applicationComponent)
            .build()
            .inject(this)
    }

    override fun initialiseView() {
        val country: Country = intent.getParcelableExtra<Country>("country")!!
        presenter.showCountryDetail(country)
        }

    override fun getCountryDetail() {
       val country: Country = intent.getParcelableExtra<Country>("country")!!
        presenter.showCountryDetail(country)
    }

    override fun showCountryDetail(country: Country) {
        val countryNumeric : String? = country.numericCode.toString()
        val countryName : String? = country.name
        val countryRegion : String? = country.region
        val countryFlag : String? = country.flag
        val dot   = "."
        val plus_text = "+"
        val area_unit = "sq m"
        val aboutText = "About"
        val nameText : String? ="$aboutText ${ countryName}"
        country_name_tv?.text = nameText
        country_area_tv?.text = "${country.area.toString()} $area_unit"
        country_capital_tv?.text = country.capital
        country_continent_tv?.text = country.region
        if(!country.callingCodes?.isEmpty()!!)
        {
            country_callcode_tv?.text="$plus_text${country.callingCodes?.get(0)}"
        }
        country_population_tv?.text = country.population.toString()
        country_language_tv?.text = country.language
        country_currency_tv?.text = country.currency

        val flagUri = Uri.parse(countryFlag)
        GlideToVectorYou
            .init()
            .with(this)
            .load(flagUri, country_flag_iv)


    }
}




