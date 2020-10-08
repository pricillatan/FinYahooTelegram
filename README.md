# yahoofintelegram
Yahoo Finance API + Telegram interval reporting

#Dependencies
1) Making use of Yahoo API from https://english.api.rakuten.net/
2) Telegram API https://core.telegram.org/

# SpringBoot App
> Scheduler / cron job

# Update these values
> YAHOO_FIN_URL=[YAHOO URL]

> YAHOO_FIN_KEY=[YAHOO KEY]

> YAHOO_FIN_HOST=[YAHOO HOST]

> FIN_REGION=[REGION]

> FIN_SYMBOLS=[SYMBOLS]

> TELEGRAM_TOKEN=[TELEGRAM TOKEN]

> TELEGRAM_CHANNEL_ID=[TELEGRAM CHANNEL]

> app.scheduler.cron=[CRON SCHEDULER VAR]
  
# Deployment to HerokuApp
GUI:
1) Create App in https://dashboard.heroku.com/
2) Connect to github
3) Settings > Add Config Vars
> YAHOO_FIN_URL=[YAHOO URL]

> YAHOO_FIN_KEY=[YAHOO KEY]

> YAHOO_FIN_HOST=[YAHOO HOST]

> FIN_REGION=[REGION]

> FIN_SYMBOLS=[SYMBOLS]

> TELEGRAM_TOKEN=[TELEGRAM TOKEN]

> TELEGRAM_CHANNEL_ID=[TELEGRAM CHANNEL]

> app.scheduler.cron=0 0,15,30,45 1-10 * * MON-FRI
