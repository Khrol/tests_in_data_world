class DevelopersByCountries(object):
    def calculate(self, developers, countries):
        joined_data = developers[['country_id']].merge(countries,
            how='left', left_on='country_id', right_on='id')
        result = joined_data.groupby('name').size().to_frame().reset_index()
        result.columns = ['country', 'count']
        return result
