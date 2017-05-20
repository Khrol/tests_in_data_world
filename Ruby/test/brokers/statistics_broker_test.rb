require 'test_helper'

class StatisticsBrokerTest < ActiveSupport::TestCase
  test 'developers_by_country' do
    country1 = create :country, name: 'Russia'
    country2 = create :country, name: 'Belarus'

    create :developer, name: 'Ivan', country: country1
    create :developer, name: 'Petr', country: country1
    create :developer, name: 'Dmitry', country: country1

    create :developer, name: 'Yas', country: country2
    create :developer, name: 'Lyavon', country: country2

    assert_equal(
      {'Russia' => 3, 'Belarus' => 2},
      StatisticsBroker.new.developers_by_countries
    )
  end
end
