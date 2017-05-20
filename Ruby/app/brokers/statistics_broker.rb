class StatisticsBroker
  def developers_by_countries
    Country.joins(:developers).group('countries.name').count
  end
end
